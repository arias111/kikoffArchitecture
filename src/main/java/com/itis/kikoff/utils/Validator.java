package com.itis.kikoff.utils;

import com.itis.kikoff.dto.SignInDto;
import com.itis.kikoff.dto.SignUpDto;
import com.itis.kikoff.models.auth.User;

import com.itis.kikoff.services.users.UsersService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class Validator extends ResponseCreator {

    private final int MIN_PASSWORD_LENGTH = 5;
    private final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
    private final UsersService userService;

    public Validator(UsersService userService) {
        this.userService = userService;
    }

    public Optional<ErrorEntity> getLoginFormError(SignInDto form) {
        Optional<User> optionalUserEntity = userService.findOneByEmail(form.getEmail());
        if (!optionalUserEntity.isPresent()) {
            return Optional.of(ErrorEntity.USER_NOT_FOUND);
        }
        User userEntity = optionalUserEntity.get();
        if (userEntity.getHashPassword().equals(form.getPassword()) == false) {
            return Optional.of(ErrorEntity.INCORRECT_PASSWORD);
        }
        return Optional.empty();
    }

    public Optional<ErrorEntity> getUserRegisterFormError(SignUpDto signUpDto) {
        if (signUpDto.getEmail() == null || emailPattern.matcher(signUpDto.getEmail()).matches() == false) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (signUpDto.getPassword() == null || signUpDto.getPassword().length() < MIN_PASSWORD_LENGTH) {
            return Optional.of(ErrorEntity.PASSWORD_TOO_SHORT);
        }
        if (userService.findOneByEmail(signUpDto.getEmail()).isPresent()) {
            return Optional.of(ErrorEntity.EMAIL_ALREADY_TAKEN);
        }
        return Optional.empty();
    }
}
