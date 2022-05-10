package com.itis.kikoff.services;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.TokenDto;
import com.itis.kikoff.repositories.TokenRepository;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public TokenDto signIn(EmailPasswordDto emailPasswordDto) {
        Optional<User> optionalUser = userRepository.findByEmail(emailPasswordDto.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(emailPasswordDto.getPassword(), user.getHashPassword())) {
                String token = tokenService.generateToken(user);
                return TokenDto.builder()
                        .token(token)
                        .build();
            } else {
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            throw new EntityNotFoundException("User not found");
        }
    }
}
