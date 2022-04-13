package com.itis.kikoff.services.signUp;

import com.itis.kikoff.dto.SignUpFormDto;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.enums.State;
import com.itis.kikoff.repositories.UserRepository;
//import com.itis.kikoff.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private MailsService mailsService;
//

    @Override
    public void signUpUser(SignUpFormDto form) {
        User newUser = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .email(form.getEmail())
                .birthday(form.getBirthday())
                .creationDate(LocalDateTime.now())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .state(State.BANNED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();


        usersRepository.save(newUser);
//        smsService.sendSms(form.getEmail(), "Вы зарегистрированы!");

        //mailsService.sendEmailForConfirm(newUser.getEmail(), newUser.getConfirmCode()); }

//    @Override
//    public boolean userWithSuchEmailExists(String email) {
//        return usersRepository.existsByEmail(email);
//    }
    }
}
