package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.*;

import java.util.List;

public interface CategoryService {
    List<CategoryRespDto> getAll();
    List<ProductRespDto> getProducts(Long id);
    void createCategory(CategoryDto categoryDto);
}
