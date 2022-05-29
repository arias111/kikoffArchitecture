package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.BillDto;
import com.itis.kikoff.models.dto.BillIdDto;
import com.itis.kikoff.services.BillService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("purchaseController")
public class PurchaseController {

    @Autowired
    private BillService billService;

    @PostMapping("/bill/success")
    public ResponseEntity<BillIdDto> successBill(@RequestHeader("X-TOKEN") String token, @RequestBody BillIdDto billIdDto) {
        return ResponseEntity.ok(billService.successBill(billIdDto));
    }

}
