package com.itis.kikoff.repositories;


import com.itis.kikoff.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByIsDeletedIsNull();
   // User getUser(Long userId);
}