package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.CategoryRepository;
import com.itis.kikoff.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoriesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<CategoryRespDto> getAll() {
        return CategoryRespDto.from(categoriesRepository.findAll());
    }

    @Override
    public List<ProductRespDto> getProducts(Long id) {
        List<Product> products = productRepository.findAllByCategories_Id(id);
        List<ProductRespDto> result = ProductRespDto.from(products);
        return result;
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoriesRepository.save(CategoryDto.to(categoryDto));
    }
}
