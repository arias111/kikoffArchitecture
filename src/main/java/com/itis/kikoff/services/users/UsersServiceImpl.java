package com.itis.kikoff.services.users;

import com.itis.kikoff.dto.ProfileUpdateDto;
import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.exceptions.InvalidTokenException;
import com.itis.kikoff.exceptions.NotFoundException;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.security.JwtHelper;
import com.itis.kikoff.utils.ErrorEntity;
import com.itis.kikoff.utils.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import static com.itis.kikoff.dto.UserDto.from;
@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService userService;

    @Override
    public User getByEmail(String email) {
       return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }
    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return from(userRepository.findAllByIsDeletedIsNull());
    //return UserDto.from(userRepository.findAll());
    }

    public UserDto addUser(UserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .build();

        userRepository.save(newUser);
        return from(newUser);
    }

    public UserDto updateUser(Long userId, UserDto user) {
        User userForUpdate = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        userRepository.save(userForUpdate);
        return from(userForUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForDelete.setIsDeleted(true);
        userRepository.save(userForDelete);
    }

}

