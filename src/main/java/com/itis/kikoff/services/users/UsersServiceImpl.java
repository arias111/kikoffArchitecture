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

import java.util.Optional;

@Service
public class UsersServiceImpl extends ResponseCreator implements UsersService {


    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersService userService;

    @Override
    public UserDto save(User user) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    @Override
    public UserDto getByAuthToken(String authToken) {
        return null;
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean update(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }


    public UserDto getUserProfileInfo(String authorization) {
        String token = JwtHelper.getTokenFromHeader(authorization);
        if (token == null || jwtHelper.validateToken(token) == false) {
            throw new InvalidTokenException("Ошибка авторизации");
        }
        String email = jwtHelper.getEmailFromToken(token);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String tokenPassword = jwtHelper.getPasswordFromToken(token);
            if (tokenPassword.equals(user.getHashPassword()) == false) {
                throw new InvalidTokenException("Wrong password");
            }
            UserDto userDto = new UserDto();
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setPatronymic(user.getPatronymic());
            userDto.setEmail(user.getEmail());
            userDto.setBirthday(user.getBirthday());
            return userDto;
        } else {
            throw new NotFoundException("User with email " + email + " not found");
        }
    }

    @Override
    public ResponseEntity changePassword(String authorization, ProfileUpdateDto profileUpdateDto) {
        String token = JwtHelper.getTokenFromHeader(authorization);
        if (profileUpdateDto.getPassword().length() > 5 && profileUpdateDto.getPassword() != null) {
            User user = userService.getByEmail(jwtHelper.getEmailFromToken(token));
            if (profileUpdateDto.getPassword().equals(user.getHashPassword())) {
                return createErrorResponse(ErrorEntity.DUPLICATE_PASSWORD);
            } else {
                user.setHashPassword(profileUpdateDto.getPassword());
                userRepository.save(user);
                return createGoodResponse("Password has been changed");
            }
        } else {
            return createErrorResponse(ErrorEntity.INCORRECT_PASSWORD);
        }
    }

    @Override
    public ResponseEntity changeUserName(String authorization, ProfileUpdateDto profileUpdateDto) {
        String token = JwtHelper.getTokenFromHeader(authorization);
        User user = userService.getByEmail(jwtHelper.getEmailFromToken(token));
        user.setFirstName(profileUpdateDto.getName());
        userRepository.save(user);
        return createGoodResponse("Name has been changed");
    }
}

