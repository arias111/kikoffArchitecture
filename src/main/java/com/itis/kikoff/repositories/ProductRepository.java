package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategories_Id(Long categoryId);
}
