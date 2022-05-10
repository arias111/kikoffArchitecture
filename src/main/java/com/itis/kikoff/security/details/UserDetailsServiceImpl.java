package com.itis.kikoff.security.details;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.TokenRepository;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findById(Long.parseLong(id));
        if (optional.isPresent()) {
            return new UserDetailsImpl(optional.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
