package com.itis.kikoff.controllers;

import com.itis.kikoff.dto.ProfileUpdateDto;
import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.security.JwtHelper;
import com.itis.kikoff.services.users.UsersService;
import com.itis.kikoff.utils.ResponseCreator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController extends ResponseCreator {

    @Autowired
    private UsersService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ApiOperation(value = "Добавление юзера")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Успешно добавлено", response = UserDto.class)})
    @PostMapping("/users")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/users/{user-id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/profile")
//    public ResponseEntity<UserDto> getProfile(@RequestHeader("X-TOKEN") String token){
//        UserDto dto = userService.getUserProfileInfo(token);
//        return createGoodResponse(dto);
//    }

    @GetMapping("/user_info/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
