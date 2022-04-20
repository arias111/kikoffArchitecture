package com.itis.kikoff.controllers;

import com.itis.kikoff.dto.SignInDto;
import com.itis.kikoff.dto.SignUpDto;
import com.itis.kikoff.dto.TokenDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.security.JwtHelper;
import com.itis.kikoff.services.auth.AuthService;
import com.itis.kikoff.services.users.UsersService;
import com.itis.kikoff.utils.ErrorEntity;
import com.itis.kikoff.utils.ResponseCreator;
import com.itis.kikoff.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController extends ResponseCreator {
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UsersService userService;
    @Autowired
    private AuthService authService;
    @Autowired
    private Validator validator;

    @PostMapping("/sign-up")
    ResponseEntity registrationUser(@RequestBody SignUpDto signUpDto) {
        Optional<ErrorEntity> errorEntity = validator.getUserRegisterFormError(signUpDto);
        if(errorEntity.isPresent()) {
            return createErrorResponse(errorEntity.get());
        }
        return createGoodResponse(authService.userRegistration(signUpDto));
    }

    @PostMapping("/sign-in")
    ResponseEntity loginUser(@RequestBody SignInDto signInDto) {
        User user = userService.getByEmail(signInDto.getEmail());
        return createGoodResponse(new TokenDto(jwtHelper.generateToken(user)));
    }
}
