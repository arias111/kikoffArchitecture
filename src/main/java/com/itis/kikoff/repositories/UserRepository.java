package com.itis.kikoff.repositories;

import com.itis.kikoff.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
