package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.*;
import com.itis.kikoff.services.BillProductService;
import com.itis.kikoff.services.BillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("purchasePreparationController")
public class PurchasePreparationController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillProductService billProductService;

    @PostMapping("/bill/create")
    public ResponseEntity<BillIdDto> createBill(@RequestBody BillDto billDto) {
        return ResponseEntity.ok(billService.createBill(billDto));
    }

    @PostMapping("/bill/fill")
    public ResponseEntity<?> fillBill(@RequestBody BillProductDto billProductDto) {
        billProductService.fill(billProductDto);
        return ResponseEntity.ok("");
    }
}
