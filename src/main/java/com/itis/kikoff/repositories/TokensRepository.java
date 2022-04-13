package com.itis.kikoff.repositories;

import com.itis.kikoff.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}

