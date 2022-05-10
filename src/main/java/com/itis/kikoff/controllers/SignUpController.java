package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.SignUpDto;
import com.itis.kikoff.models.dto.TokenDto;
import com.itis.kikoff.services.SignInService;
import com.itis.kikoff.services.SignUpService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("signUpController")
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignUpDto signUpDto) throws Throwable {
        return ResponseEntity.ok(signUpService.signUp(signUpDto));
    }
}
