package com.itis.kikoff.services;

import com.auth0.jwt.JWT;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.dto.UserDto;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getProfile(String token) {
        Long id = Long.parseLong(JWT.decode(token).getSubject());
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserDto.from(user);
        } else {
            throw new EntityNotFoundException("user not found");
        }
    }
}
