package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.models.dto.ProductIdDto;
import com.itis.kikoff.models.dto.ProductRespDto;
import com.itis.kikoff.models.shop.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(ProductDto productDto);
    void deleteProduct(ProductIdDto productIdDto);
    List<ProductRespDto> getAll();
    ProductDto getProduct(Long id);
}
