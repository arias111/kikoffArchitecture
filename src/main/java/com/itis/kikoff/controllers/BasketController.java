package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.services.BasketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("basketController")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/basket/get/{basket-id}")
    public ResponseEntity<BasketDto> getProducts(@RequestHeader("X-TOKEN") String token, @PathVariable("basket-id") Long basketId) {
        return ResponseEntity.ok(basketService.getBasket(basketId));
    }

    @PostMapping("/basket/createBasket")
    public ResponseEntity<BasketIdDto> createBasket(@RequestHeader("X-TOKEN") String token, @RequestBody BasketDto basketDto) {;
        return ResponseEntity.ok(basketService.createBasket(basketDto));
    }

    @PostMapping("/basket/deleteBasket")
    public ResponseEntity<?> deleteBasket(@RequestHeader("X-TOKEN") String token, @RequestBody BasketIdDto basketIdDto) {
        basketService.deleteBasket(basketIdDto);
        return ResponseEntity.ok("");
    }
}
