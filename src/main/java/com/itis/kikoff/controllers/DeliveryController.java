package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.AddressDto;
import com.itis.kikoff.models.dto.BasketDto;
import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.services.AddressService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Api("deliveryController")
public class DeliveryController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/delivery")
    public ResponseEntity<?> saveAddress(@RequestBody AddressDto addressDto) {
        addressService.saveAddress(addressDto);
        return ResponseEntity.ok(" ");
    }

}
