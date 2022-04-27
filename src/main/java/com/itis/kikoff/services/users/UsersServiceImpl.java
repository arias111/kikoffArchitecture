package com.itis.kikoff.services.users;

import com.itis.kikoff.dto.UserDto;
import com.itis.kikoff.exceptions.NotFoundException;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static com.itis.kikoff.dto.UserDto.from;
@Service
public class UsersServiceImpl implements UsersService {


    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepository userRepository;

    public UsersServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


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
    private final PasswordEncoder passwordEncoder;
    public UserDto addUser(UserDto user) {
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .creationDate(LocalDateTime.now())
                .hashPassword(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
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

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
    }


//    public UserDto getUserProfileInfo(String authorization) {
//        String token = JwtHelper.getTokenFromHeader(authorization);
//        if (token == null || jwtHelper.validateToken(token) == false) {
//            throw new InvalidTokenException("Ошибка авторизации");
//        }
//        String email = jwtHelper.getEmailFromToken(token);
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String tokenPassword = jwtHelper.getPasswordFromToken(token);
//            if (tokenPassword.equals(user.getHashPassword()) == false) {
//                throw new InvalidTokenException("Wrong password");
//            }
//            UserDto userDto = new UserDto();
//            userDto.setFirstName(user.getFirstName());
//            userDto.setLastName(user.getLastName());
//            userDto.setPatronymic(userDto.getPatronymic());
//            userDto.setBirthday(userDto.getBirthday());
//            userDto.setEmail(user.getEmail());
//            return userDto;
//        } else {
//            throw new NotFoundException("User with email " + email + " not found");
//        }
//    }


}

