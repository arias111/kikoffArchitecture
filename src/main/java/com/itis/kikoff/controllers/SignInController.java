package com.itis.kikoff.controllers;

import com.itis.kikoff.dto.SignInForm;
import com.itis.kikoff.dto.TokenDto;
import com.itis.kikoff.services.signIn.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> login(@RequestBody SignInForm form) {
        return ResponseEntity.ok(signInService.signIn(form));
    }
}
