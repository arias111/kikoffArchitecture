package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Address;
import com.itis.kikoff.models.shop.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
