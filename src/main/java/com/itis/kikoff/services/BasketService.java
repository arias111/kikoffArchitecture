package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BasketDto;
import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.BasketProductDto;
import com.itis.kikoff.models.dto.ProductIdDto;

public interface BasketService {
    BasketDto getBasket(Long id);
    BasketIdDto createBasket(BasketDto basketDto);
    void deleteBasket(BasketIdDto basketIdDto);
}
