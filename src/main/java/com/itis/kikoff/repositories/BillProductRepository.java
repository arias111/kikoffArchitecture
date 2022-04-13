package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProductRepository extends JpaRepository<BillProduct, Long> {
    void deleteAllByProduct_Id(Long productId);
}
