package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.shop.Basket;
import com.itis.kikoff.models.shop.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductIdDto {
    private Long productId;
    public static List<ProductIdDto> from(List<Product> list) {
        List<ProductIdDto> result = new ArrayList<>();
        for (Product product : list) {
            ProductIdDto productIdDto = ProductIdDto.builder()
                            .productId(product.getId())
                                    .build();
            result.add(productIdDto);
        }
        return result;
    }
}
