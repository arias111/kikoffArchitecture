package com.itis.kikoff.controllers;

import com.itis.kikoff.dto.ProfileUpdateDto;
import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.security.JwtHelper;
import com.itis.kikoff.services.users.UsersService;
import com.itis.kikoff.utils.ResponseCreator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController extends ResponseCreator {

    private final JwtHelper jwtHelper;
    private final UsersService userService;

    @GetMapping
    ResponseEntity getProfile(@RequestHeader String authorization) {
        UserDto dto = userService.getUserProfileInfo(authorization);
        return createGoodResponse(dto);
    }

    @DeleteMapping
    ResponseEntity deleteProfile(@RequestHeader String authorization) {
        String token = JwtHelper.getTokenFromHeader(authorization);
        User user = userService.getByEmail(jwtHelper.getEmailFromToken(token));
        userService.delete(user);
        return createGoodResponse("Deleted");
    }

    @PutMapping(path = "/change")
    ResponseEntity changeUserName(@RequestHeader String authorization,@RequestBody ProfileUpdateDto profileUpdateDto) {
        return userService.changeUserName(authorization, profileUpdateDto);
    }

    @PostMapping(path = "/change")
    ResponseEntity changePassword(@RequestHeader String authorization,@RequestBody ProfileUpdateDto profileUpdateDto) {
        return userService.changePassword(authorization, profileUpdateDto);
    }
}
