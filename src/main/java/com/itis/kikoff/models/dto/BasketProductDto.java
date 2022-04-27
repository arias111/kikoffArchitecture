package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.shop.Basket;
import com.itis.kikoff.models.shop.BasketProduct;
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
public class BasketProductDto {
    private Long basketId;
    private Long productId;
    public static BasketProduct to(Basket basket, Product product) {
        return BasketProduct.builder()
                .basket(basket)
                .product(product)
                .build();
    }
//    public static BasketProduct to1(Long userId, List<Product> productList) {
//
//        return BasketProduct.builder()
//                .basket(basket)
//                .product(product)
//                .build();
//    }
//    private Basket basket;
//    private Product product;
}
