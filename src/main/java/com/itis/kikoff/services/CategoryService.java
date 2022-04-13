package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.CategoryDto;
import com.itis.kikoff.models.dto.ProductDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    List<ProductDto> getProducts(CategoryDto categoryDto);
    void createCategory(CategoryDto categoryDto);
}
