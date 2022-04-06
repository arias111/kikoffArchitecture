package com.itis.kikoff.repositories;

import com.itis.kikoff.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional <User> findByConfirmCode(String confirmCode);
}
