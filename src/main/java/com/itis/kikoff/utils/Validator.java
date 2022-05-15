package com.itis.kikoff.utils;

import com.itis.kikoff.models.dto.SignUpDto;
import com.itis.kikoff.models.auth.User;

//import com.itis.kikoff.services.users.UsersService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class Validator extends ResponseCreator {

    private final int MIN_PASSWORD_LENGTH = 5;
    private final Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");


    public Optional<ErrorEntity> getUserRegisterFormError(SignUpDto signUpDto) {
        if (signUpDto.getEmail() == null || emailPattern.matcher(signUpDto.getEmail()).matches() == false) {
            return Optional.of(ErrorEntity.INVALID_EMAIL);
        }
        if (signUpDto.getPassword() == null || signUpDto.getPassword().length() < MIN_PASSWORD_LENGTH) {
            return Optional.of(ErrorEntity.PASSWORD_TOO_SHORT);
        }
        return Optional.empty();
    }
}
