package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    List<BasketProduct> findAllByBasket_Id(Long id);
    void deleteAllByProduct_Id(Long productId);
    void deleteAllByBasket_Id(Long basketId);
    void deleteByProduct_IdAndBasket_Id(Long productId, Long basketId);
}
