package com.itis.kikoff.repositories;

import com.itis.kikoff.models.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
