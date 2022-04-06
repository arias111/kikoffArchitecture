package com.itis.kikoff.services.signIn;

import com.itis.kikoff.dto.SignInForm;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SignInServiceImpl implements com.itis.kikoff.services.signIn.SignInService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signIn(SignInForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();
        usersRepository.save(user);
        return true;
    }
}
