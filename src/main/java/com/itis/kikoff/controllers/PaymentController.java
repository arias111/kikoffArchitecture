package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.CategoryRespDto;
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

    @PostMapping("/payment")
    public ResponseEntity<?> pay(@RequestHeader("X-TOKEN") String token, @RequestBody Long personalAccountId) {
        paymentService.pay(personalAccountId);
        return ResponseEntity.ok(" ");
    }
}
