package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.enums.Status;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Address;
import com.itis.kikoff.models.shop.Basket;
import com.itis.kikoff.models.shop.Bill;
import com.itis.kikoff.models.shop.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDto {
//    private Long id;
    private Long addressId;
    private Long personalAccountId;
    public static BillDto from(Bill bill) {
        return BillDto.builder()
                .addressId(bill.getAddress().getId())
                .personalAccountId(bill.getPersonalAccount().getId())
                .build();

    }
    public static Bill to(BillDto billDto, Address address, PersonalAccount personalAccount) {
        return Bill.builder()
                .billProduct(new ArrayList<>())
                .address(address)
                .personalAccount(personalAccount)
                .price(0)
                .status(Status.FAIL)
                .buyingDate(LocalDateTime.now())
                .build();

    }
}
