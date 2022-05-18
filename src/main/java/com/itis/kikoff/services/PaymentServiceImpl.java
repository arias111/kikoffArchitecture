package com.itis.kikoff.services;

import com.auth0.jwt.JWT;
import com.itis.kikoff.models.enums.Status;
import com.itis.kikoff.models.payments.Payment;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.repositories.PaymentRepository;
import com.itis.kikoff.repositories.PersonalAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @Override
    public void pay(String token) {
        Long id = Long.parseLong(JWT.decode(token).getSubject());
        PersonalAccount personalAccount = personalAccountRepository.getByUser_Id(id);
        Payment payment = Payment.builder()
                .status(Status.SUCCESS)
                .sum(5000)
                .personalAccount(personalAccount)
                .transactionDate(LocalDateTime.now())
                .build();
        int sum = personalAccount.getBalance();
        personalAccount.setBalance(sum + 5000);
        personalAccountRepository.save(personalAccount);
        paymentRepository.save(payment);
    }

    @Override
    public int getBalance(String token) {
        Long id = Long.parseLong(JWT.decode(token).getSubject());
        Optional<PersonalAccount> optionalPersonalAccount = personalAccountRepository.findByUser_Id(id);
        if (optionalPersonalAccount.isPresent()) {
            int balance = optionalPersonalAccount.get().getBalance();
            return balance;
        } else {
            throw new EntityNotFoundException("Personal Account not found");
        }
    }
}
