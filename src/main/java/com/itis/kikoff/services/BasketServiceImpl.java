package com.itis.kikoff.services;

import com.itis.kikoff.models.auth.User;
import com.itis.kikoff.models.dto.BasketDto;
import com.itis.kikoff.models.dto.BasketIdDto;
import com.itis.kikoff.models.payments.PersonalAccount;
import com.itis.kikoff.models.shop.Basket;
import com.itis.kikoff.models.shop.BasketProduct;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private PersonalAccountRepository personalAccountRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public BasketDto getBasket(Long id) {
        Long basketId = id;
        Basket basket;
        User user;
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        } else {
            throw new EntityNotFoundException();
        }
        List<BasketProduct> basketProductList = basketProductRepository.findAllByBasket_Id(basketId);
        List<Product> productList = new ArrayList<>();
        for (BasketProduct basketProduct : basketProductList) {
            Optional<Product> optionalProduct = productRepository.findById(basketProduct.getId());
            optionalProduct.ifPresent(productList::add);
        }
        Optional<User> optionalUser = userRepository.findById(basket.getUser().getId());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new EntityNotFoundException();
        }
        return BasketDto.from(basketId, user, productList);
    }

    @Override
    public BasketIdDto createBasket(BasketDto basketDto) {
        Optional<User> optionalUser = userRepository.findById(basketDto.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            basketRepository.save(BasketDto.to(user));
            Optional<Basket> optionalBasket = basketRepository.findByUser_Id(basketDto.getUserId());
            if (optionalBasket.isPresent()) {
                return BasketIdDto.builder().basketId(optionalBasket.get().getId()).build();
            } else {
                throw new EntityNotFoundException();
            }
        } else  {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteBasket(BasketIdDto basketIdDto) {
        basketProductRepository.deleteAllByBasket_Id(basketIdDto.getBasketId());
        basketRepository.deleteById(basketIdDto.getBasketId());
    }


}
