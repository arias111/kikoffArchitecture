package com.itis.kikoff.security.details;

import com.itis.kikoff.models.Token;
import com.itis.kikoff.repositories.TokensRepository;
import com.itis.kikoff.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;


// чтобы Spring Security мог получить нашего пользователя по емайлу

@Component("tokenUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokensRepository tokensRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Token result = tokensRepository.findByToken(token).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("Token not found"));
        return new UserDetailsImpl(result.getUser());
    }

}

