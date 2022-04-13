package com.itis.kikoff.controllers;

import com.itis.kikoff.models.dto.BasketDto;
import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.models.dto.ProductIdDto;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.services.BasketService;
import com.itis.kikoff.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@Api("productController")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/getAll")
    @ApiOperation("get one basket")
    public ResponseEntity<List<ProductDto>> getBasket() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto) {
        productService.addProduct(productDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/product/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody ProductDto productDto) {
        productService.deleteProduct(productDto);
        return ResponseEntity.ok("");
    }

    @PostMapping("/product/getProduct")
    public ResponseEntity<ProductDto> getProduct(@RequestBody ProductIdDto productIdDto) {
        return ResponseEntity.ok(productService.getProduct(productIdDto.getProductId()));
    };

}
