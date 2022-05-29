package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.payments.PersonalAccount;
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
public class BasketDto {
    private Long userId;
    private List<ProductDto> productList;
    public static BasketDto from(Long basketId, User user, List<Product> products) {
        return BasketDto.builder()
                .userId(user.getId())
                .productList(ProductDto.from(products)).build();
    }
    public static Basket to(User user) {
        return Basket.builder()
                .user(user)
                .basketProducts(new ArrayList<>())
                .build();
    }
}
