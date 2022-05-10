package com.itis.kikoff.repositories;

import com.itis.kikoff.models.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUser_Id(Long id);
    void deleteByUser_Id(Long id);
}
