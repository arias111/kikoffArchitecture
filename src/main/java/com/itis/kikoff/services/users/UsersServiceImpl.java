package com.itis.kikoff.services.users;

import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.itis.kikoff.dto.UserDto.*;
@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }
    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = usersRepository.findById(userId);
        return UserDto.from(user.orElse(User.builder().build()));
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .patronymic(userDto.getPatronymic())
                .birthday(userDto.getBirthday())
                .email(userDto.getEmail())
//                .hashPassword((passwordEncoder.encode(signUpForm.getPassword())))
                .role(Role.USER)
                .build();
        usersRepository.save(user);
        return from(user);
    }
    @Override
    public UserDto updateUser(Long userId, UserDto user) {
        User userForUpdate = usersRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
        usersRepository.save(userForUpdate);
        return from(userForUpdate);
    }

    @Override
    public void deleteUser(Long userId) {
        User userForDelete = usersRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        userForDelete.setIsDeleted(true);
        usersRepository.save(userForDelete);
    }
}

