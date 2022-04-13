//package com.itis.kikoff.controllers;
//
//import com.itis.kikoff.security.details.UserDetailsImpl;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class PersonalAccountController {
//    @GetMapping("/personal_account")
//    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
//        String email = userDetails.getUsername();
//        model.addAttribute("email", email);
//        return "personal_account";
//    }
//
//}
