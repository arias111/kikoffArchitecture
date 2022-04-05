package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}
