package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.BasketProductDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.services.ProductAndBasketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("productAndBasketController")
public class ProductAndBasketController {

    @Autowired
    private ProductAndBasketService productAndBasketService;

    @PostMapping("/basket/product/addToBasket")
    public ResponseEntity<?> addToBasket(@RequestHeader("X-TOKEN") String token, @RequestBody BasketProductDto basketProductDto) {
        productAndBasketService.addToBasket(basketProductDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/basket/product/deleteFromBasket")
    public ResponseEntity<?> deleteFromBasket(@RequestHeader("X-TOKEN") String token, @RequestBody BasketProductDto basketProductDto) {
        productAndBasketService.deleteFromBasket(basketProductDto);
        return ResponseEntity.ok("");
    }

//    @PostMapping("/basket/product/getProducts")
//    public ResponseEntity<List<ProductDto>> getProductsFromBasket(@RequestHeader("X-TOKEN") String token, @RequestBody BasketIdDto basketIdDto) {
//        return ResponseEntity.ok(productAndBasketService.getProductsFromBasket(basketIdDto));
//    }

    @GetMapping("/basket/product/getProducts/{basket-id}")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestHeader("X-TOKEN") String token, @PathVariable("basket-id") Long basketId) {
        return ResponseEntity.ok(productAndBasketService.getProductsFromBasket(basketId));
    }

}
