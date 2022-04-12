package com.itis.kikoff.services.users;
import com.itis.kikoff.dto.UserDto;
import java.util.List;

public interface UsersService {

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    UserDto addUser(UserDto user);

    UserDto updateUser(Long userId,UserDto user);

    void deleteUser(Long userId);
}
