package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.BasketProductDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.services.ProductAndBasketService;
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
@Api("productAndBasketController")
public class ProductAndBasketController {

    @Autowired
    private ProductAndBasketService productAndBasketService;

    @PostMapping("/basket/product/addToBasket")
    public ResponseEntity<?> addToBasket(@RequestBody BasketProductDto basketProductDto) {
        productAndBasketService.addToBasket(basketProductDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/basket/product/deleteFromBasket")
    public ResponseEntity<?> deleteFromBasket(@RequestBody BasketProductDto basketProductDto) {
        productAndBasketService.deleteFromBasket(basketProductDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/basket/product/getProducts")
    public ResponseEntity<List<ProductDto>> getProductsFromBasket(@RequestBody BasketIdDto basketIdDto) {
        return ResponseEntity.ok(productAndBasketService.getProductsFromBasket(basketIdDto));
    }
}