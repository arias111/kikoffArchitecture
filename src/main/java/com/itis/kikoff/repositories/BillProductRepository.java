package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillProductRepository extends JpaRepository<BillProduct, Long> {
}
