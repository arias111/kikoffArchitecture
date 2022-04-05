package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
