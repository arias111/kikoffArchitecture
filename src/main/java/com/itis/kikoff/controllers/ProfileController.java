package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.UserDto;
import com.itis.kikoff.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(userService.getProfile(token));
    }
}
