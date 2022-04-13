package com.itis.kikoff.services.users;
import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;

import java.util.List;

public interface UsersService {

    void blockUser(Long userId);
    User getUser(Long userId);

//    List<UserDto> getAllUsers();
//    UserDto addUser(UserDto user);

}
