package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.services.BasketService;
import com.itis.kikoff.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("productController")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/getAll")
    @ApiOperation("get all products")
    public ResponseEntity<List<ProductRespDto>> getBasket(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestHeader("X-TOKEN") String token, @RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/product/delete")
    public ResponseEntity<?> deleteProduct(@RequestHeader("X-TOKEN") String token, @RequestBody ProductIdDto productIdDto) {
        productService.deleteProduct(productIdDto);
        return ResponseEntity.ok("");
    }

    @GetMapping("/product/getProducts/{product-id}")
    public ResponseEntity<ProductDto> getProducts(@RequestHeader("X-TOKEN") String token, @PathVariable("product-id") Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }


}
