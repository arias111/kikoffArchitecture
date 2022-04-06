package com.itis.kikoff.services.signUp;

import com.itis.kikoff.dto.SignUpForm;
import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Role;
import com.itis.kikoff.models.enums.State;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.services.mail.MailsService;
//import com.itis.kikoff.services.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailsService mailsService;

//    @Autowired
//    private SmsService smsService;

    @Override
    public boolean signUp(SignUpForm form) {
        User newUser = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .patronymic(form.getPatronymic())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .state(State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .role(Role.USER)
                .build();


        usersRepository.save(newUser);
//        smsService.sendSms(form.getEmail(), "Вы зарегистрированы!");

        mailsService.sendEmailForConfirm(newUser.getEmail(), newUser.getConfirmCode());
        return true;
    }
}
