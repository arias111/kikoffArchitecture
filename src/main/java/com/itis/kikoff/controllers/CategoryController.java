package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.BasketDto;
import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.CategoryDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.services.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("categoryController")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping("/categories/get")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestBody CategoryDto categoryDto) {;
        return ResponseEntity.ok(categoryService.getProducts(categoryDto));
    }

    @PostMapping("/categories/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.ok("");
    }

}
