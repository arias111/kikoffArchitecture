package com.itis.kikoff.services;

import com.itis.kikoff.models.dto.ProductDto;
import com.itis.kikoff.models.shop.Category;
import com.itis.kikoff.models.shop.Product;
import com.itis.kikoff.repositories.BasketProductRepository;
import com.itis.kikoff.repositories.BillProductRepository;
import com.itis.kikoff.repositories.CategoryRepository;
import com.itis.kikoff.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillProductRepository billProductRepository;

    @Autowired
    private CategoryRepository categoriesRepository;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @Override
    public void addProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoriesRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            productRepository.save(ProductDto.to(productDto, category));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void deleteProduct(ProductDto productDto) {
        Product product;
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent()) {
            product = optionalProduct.get();
        } else { return; }
        productRepository.delete(product);
        billProductRepository.deleteAllByProduct_Id(product.getId());
        basketProductRepository.deleteAllByProduct_Id(product.getId());
    }

    @Override
    public List<ProductDto> getAll() {
        return ProductDto.from(productRepository.findAll());
    }

    @Override
    public ProductDto getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return ProductDto.fromOne(optionalProduct.get());
        } else {
            throw new EntityNotFoundException();
        }
    }
}
