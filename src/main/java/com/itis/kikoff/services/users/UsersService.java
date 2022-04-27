package com.itis.kikoff.services.users;

import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User getByEmail(String email);
    Optional<User> findOneByEmail(String email);
    List<UserDto> getAllUsers();
    UserDto addUser(UserDto user);

    UserDto updateUser(Long userId, UserDto user);
  //  UserDto getUserProfileInfo(String token);
    void deleteUser(Long userId);
    User getUser(Long userId);
}
