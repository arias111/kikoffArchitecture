package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
