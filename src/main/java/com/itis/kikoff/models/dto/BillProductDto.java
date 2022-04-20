package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.shop.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillProductDto {
    private Long billId;
    private List<ProductIdDto> productList;
    public static BillProduct to(Bill bill, Product product) {
        return BillProduct.builder()
                .bill(bill)
                .product(product)
                .build();
    }
}
