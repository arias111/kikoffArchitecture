package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BillProductDto;
import com.itis.kikoff.models.dto.ProductIdDto;
import com.itis.kikoff.models.shop.Bill;
import com.itis.kikoff.models.shop.BillProduct;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.BillProductRepository;
import com.itis.kikoff.repositories.BillRepository;
import com.itis.kikoff.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillProductServiceImpl implements BillProductService {

    @Autowired
    private BillProductRepository billProductRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void fill(BillProductDto billProductDto) {
        List<Product> productList = new ArrayList<>();
        int price = 0;
        for (ProductIdDto productIdDto : billProductDto.getProductList()) {
            Optional<Product> optionalProduct = productRepository.findById(productIdDto.getProductId());
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                productList.add(product);
                price += product.getPriceOfOne();
            } else {
                throw new EntityNotFoundException();
            }
        }
        Bill bill = billRepository.getById(billProductDto.getBillId());
        bill.setPrice(price);
        billRepository.save(bill);
        for (Product product : productList) {
            billProductRepository.save(BillProduct.builder()
                    .bill(bill)
                    .product(product)
                    .build());
        }
    }
}
