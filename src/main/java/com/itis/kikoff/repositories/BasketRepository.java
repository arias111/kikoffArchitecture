package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
