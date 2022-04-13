package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.BasketProductDto;
import com.itis.kikoff.models.dto.ProductDto;

import java.util.List;

public interface ProductAndBasketService {
    void addToBasket(BasketProductDto basketProductDto);
    void deleteFromBasket(BasketProductDto basketProductDto);
    List<ProductDto> getProductsFromBasket(BasketIdDto basketIdDto);
}
