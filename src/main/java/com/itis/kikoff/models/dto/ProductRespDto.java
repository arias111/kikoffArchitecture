package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.shop.Category;
import com.itis.kikoff.models.shop.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRespDto {
    private Long id;
    private Integer countOfProducts;
    private Integer priceOfOne;
    private String name;
    private Long categoryId;
    public static List<ProductRespDto> from(List<Product> products) {
        List<ProductRespDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductRespDto productDto = ProductRespDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .countOfProducts(product.getCountOfProducts())
                    .priceOfOne(product.getPriceOfOne())
                    .categoryId(product.getCategories().getId())
                    .build();
            result.add(productDto);
        }
        return result;
    }
    public static ProductRespDto fromOne(Product product) {
        return ProductRespDto.builder()
                .id(product.getId())
                .name(product.getName())
                .countOfProducts(product.getCountOfProducts())
                .priceOfOne(product.getPriceOfOne())
                .categoryId(product.getCategories().getId())
                .build();
    }

    public static Product to(ProductRespDto productDto, Category category) {
        return Product.builder()
                .id(productDto.getId())
                .countOfProducts(productDto.getCountOfProducts())
                .priceOfOne(productDto.getPriceOfOne())
                .name(productDto.getName())
                .categories(category)
                .basketProducts(new ArrayList<>())
                .billProductList(new ArrayList<>()).build();
    }
}
