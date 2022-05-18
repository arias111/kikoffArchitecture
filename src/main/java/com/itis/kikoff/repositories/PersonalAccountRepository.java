package com.itis.kikoff.repositories;

import com.itis.kikoff.models.payments.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {
    Optional<PersonalAccount> findByUser_Id(Long id);
    PersonalAccount getByUser_Id(Long id);
}
