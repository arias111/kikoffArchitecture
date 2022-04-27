package com.itis.kikoff.repositories;

import com.itis.kikoff.models.dto.UserIdDto;
import com.itis.kikoff.models.shop.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUser_Id(Long userId);
}
