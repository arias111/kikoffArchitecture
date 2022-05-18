package com.itis.kikoff.services;

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
    public void pay(Long id) {
        PersonalAccount personalAccount = personalAccountRepository.getById(id);
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
}
