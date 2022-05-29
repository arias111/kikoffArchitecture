package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.services.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("categoryController")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/getAll")
    public ResponseEntity<List<CategoryRespDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/categories/get/{category-id}")
    public ResponseEntity<List<ProductRespDto>> getProducts(@RequestHeader("X-TOKEN") String token, @PathVariable("category-id") Long categoryId) {
        return ResponseEntity.ok(categoryService.getProducts(categoryId));
    }

    @PostMapping("/categories/create")
    public ResponseEntity<?> createCategory(@RequestHeader("X-TOKEN") String token, @RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.ok("");
    }

}
