package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.UserDto;

public interface UserService {
    UserDto getProfile(String token);
}
