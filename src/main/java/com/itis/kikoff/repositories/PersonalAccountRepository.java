package com.itis.kikoff.repositories;

import com.itis.kikoff.models.payments.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
}
