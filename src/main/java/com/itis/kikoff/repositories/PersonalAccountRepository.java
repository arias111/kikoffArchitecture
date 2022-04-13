package com.itis.kikoff.repositories;

import com.itis.kikoff.models.payments.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
}
