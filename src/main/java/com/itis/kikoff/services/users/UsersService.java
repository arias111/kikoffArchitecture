package com.itis.kikoff.services.users;
import com.itis.kikoff.dto.ProfileUpdateDto;
import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsersService {
    UserDto save(User user);

    User getByEmail(String email);

    UserDto getByAuthToken(String authToken);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneById(Long id);

    boolean update(User user);

    boolean delete(User user);

    UserDto getUserProfileInfo(String authorization);

    ResponseEntity changePassword(String authorization, ProfileUpdateDto profileUpdateDto);

    ResponseEntity changeUserName(String authorization, ProfileUpdateDto profileUpdateDto);
}
