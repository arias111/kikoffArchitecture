package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.dto.BasketProductDto;
import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.models.shop.Basket;
import com.itis.kikoff.models.shop.BasketProduct;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.BasketProductRepository;
import com.itis.kikoff.repositories.BasketRepository;
import com.itis.kikoff.repositories.ProductRepository;
import com.itis.kikoff.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductAndBasketServiceImpl implements ProductAndBasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addToBasket(BasketProductDto basketProductDto) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketProductDto.getBasketId());
        Basket basket;
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        } else {
            throw new EntityNotFoundException();
        }
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(basketProductDto.getProductId());
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else {
            throw new EntityNotFoundException();
        }
        basketProductRepository.save(BasketProductDto.to(basket, product));
    }

    @Override
    @Transactional
    public void deleteFromBasket(BasketProductDto basketProductDto) {
        basketProductRepository.deleteByProduct_IdAndBasket_Id(basketProductDto.getProductId(), basketProductDto.getBasketId());
    }

    @Override
    public List<ProductDto> getProductsFromBasket(BasketIdDto basketIdDto) {
        List<BasketProduct> basketProductList = basketProductRepository.findAllByBasket_Id(basketIdDto.getBasketId());
        List<Product> productList = new ArrayList<>();
        for (BasketProduct basketProduct : basketProductList) {
            productList.add(basketProduct.getProduct());
        }
        return ProductDto.from(productList);
    }
}
