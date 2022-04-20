package com.itis.kikoff.services.auth;

import com.itis.kikoff.dto.SignUpDto;
import com.itis.kikoff.dto.TokenDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.security.JwtHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDto userRegistration(SignUpDto form) {
        User newUser = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .email(form.getEmail())
                .birthday(form.getBirthday())
                .creationDate(LocalDateTime.now())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .build();


        userRepository.save(newUser);
        return new TokenDto(jwtHelper.generateToken(newUser));
    }
}
