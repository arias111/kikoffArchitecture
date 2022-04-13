package com.itis.kikoff.services.signIn;

import com.itis.kikoff.dto.SignInForm;
import com.itis.kikoff.dto.TokenDto;
import com.itis.kikoff.models.Token;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.TokensRepository;
import com.itis.kikoff.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class SignInServiceImpl implements com.itis.kikoff.services.signIn.SignInService {
//
//    @Autowired
//    private Algorithm algorithm;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private RedisUsersService redisUsersService;

    @Autowired
    private TokensRepository tokensRepository;

    @SneakyThrows
    @Override
    public TokenDto signIn(SignInForm emailPassword) {
        User user = usersRepository.findByEmail(emailPassword.getEmail())
                .orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(emailPassword.getPassword(), user.getHashPassword())) {
            String tokenValue = UUID.randomUUID().toString();
            Token token = Token.builder()
                    .user(user)
                    .token(tokenValue)
                    .build();
            tokensRepository.save(token);

            return TokenDto.builder()
                    .token(tokenValue)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
