package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.EmailPasswordDto;
import com.itis.kikoff.models.dto.TokenDto;
import com.itis.kikoff.services.SignInService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("signInController")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody EmailPasswordDto emailPassword) throws Throwable {
        return ResponseEntity.ok(signInService.signIn(emailPassword));
    }
}
