package com.itis.kikoff.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.itis.kikoff.services.mail.MailsService;

@Controller
public class ConfirmController {

    @Autowired
    private MailsService mailsService;


    @GetMapping("/confirm/{code}")
    public String confirmUser(@PathVariable("code") String code) {
        Boolean isConfirmed = mailsService.isConfirmed(code);
        if (isConfirmed) {
            return "confirmed_page";
        }
        return "error_page";
    }

}
