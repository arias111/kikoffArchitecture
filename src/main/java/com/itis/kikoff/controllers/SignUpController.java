package com.itis.kikoff.controllers;

import com.itis.kikoff.dto.SignUpForm;
import com.itis.kikoff.services.signUp.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form) {
        signUpService.signUp(form);
        return "redirect:/signUp";
    }
}
