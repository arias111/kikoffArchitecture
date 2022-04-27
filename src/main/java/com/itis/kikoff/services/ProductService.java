package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.models.shop.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(ProductDto productDto);
    void deleteProduct(ProductDto productDto);
    List<ProductDto> getAll();
    ProductDto getProduct(Long id);
}
