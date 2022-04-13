package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.services.BasketService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Api("basketController")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/basket/get")
    public ResponseEntity<BasketDto> getBasket(@RequestBody BasketIdDto basketIdDto) {
        return ResponseEntity.ok(basketService.getBasket(basketIdDto));
    }

    @PostMapping("/basket/createBasket")
    public ResponseEntity<BasketIdDto> createBasket(@RequestBody BasketDto basketDto) {;
        return ResponseEntity.ok(basketService.createBasket(basketDto));
    }

    @PostMapping("/basket/deleteBasket")
    public ResponseEntity<?> deleteBasket(@RequestBody BasketIdDto basketIdDto) {
        basketService.deleteBasket(basketIdDto);
        return ResponseEntity.ok("");
    }
}
