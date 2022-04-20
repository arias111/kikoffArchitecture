package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findByAddress_IdAndPersonalAccount_Id(Long addressId, Long accountId);
    List<Bill> findAllByPersonalAccount_Id(Long id);
}
