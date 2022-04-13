package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.CategoryDto;
import com.itis.kikoff.models.dto.ProductDto;
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
    public List<CategoryDto> getAll() {
        return CategoryDto.from(categoriesRepository.findAll());
    }

    @Override
    public List<ProductDto> getProducts(CategoryDto categoryDto) {
        List<Product> products = productRepository.findAllByCategories_Id(categoryDto.getId());
        return ProductDto.from(products);
    }

    @Override
    public void createCategory(CategoryDto categoryDto) {
        categoriesRepository.save(CategoryDto.to(categoryDto));
    }
}
