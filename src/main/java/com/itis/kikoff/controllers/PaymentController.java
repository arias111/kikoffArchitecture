package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.CategoryRespDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.services.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("paymentController")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/pay")
    public ResponseEntity<?> pay(@RequestHeader("X-TOKEN") String token) {
        paymentService.pay(token);
        return ResponseEntity.ok(" ");
    }

    @GetMapping("/payment/getBalance")
    public ResponseEntity<Integer> getProducts(@RequestHeader("X-TOKEN") String token) {
        return ResponseEntity.ok(paymentService.getBalance(token));
    }
}
