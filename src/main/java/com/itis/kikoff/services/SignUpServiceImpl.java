package com.itis.kikoff.services;

import com.itis.kikoff.models.auth.Token;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.dto.SignUpDto;
import com.itis.kikoff.models.dto.TokenDto;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.repositories.PersonalAccountRepository;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @Override
    public TokenDto signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .baskets(new ArrayList<>())
                .birthday(signUpDto.getBirthday())
                .email(signUpDto.getEmail())
                .creationDate(LocalDateTime.now())
                .firstName(signUpDto.getFirstName())
                .lastName(signUpDto.getLastName())
                .hashPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .patronymic(signUpDto.getPatronymic())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        User user1 = userRepository.getByEmail(signUpDto.getEmail());
        PersonalAccount personalAccount = PersonalAccount.builder()
                .balance(30000)
                .user(user1)
                .payments(new ArrayList<>())
                .creationDate(LocalDateTime.now())
                .bills(new ArrayList<>())
                .build();
        personalAccountRepository.save(personalAccount);
        user1.setPersonalAccount(personalAccount);
        Token token = tokenService.generatingToken(user1);
        user1.setToken(token);
        userRepository.save(user1);
        return TokenDto.builder()
                .token(token.getToken())
                .build();
    }
}
