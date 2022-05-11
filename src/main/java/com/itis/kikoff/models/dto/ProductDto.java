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
public class ProductDto {
//    private Long id;
    private Integer countOfProducts;
    private Integer priceOfOne;
    private String name;
    private String url;
    private Long categoryId;
    public static List<ProductDto> from(List<Product> products) {
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = ProductDto.builder()
//                    .id(product.getId())
                    .name(product.getName())
                    .url(product.getUrl())
                    .countOfProducts(product.getCountOfProducts())
                    .priceOfOne(product.getPriceOfOne())
                    .categoryId(product.getCategories().getId())
                    .build();
            result.add(productDto);
        }
        return result;
    }
    public static ProductDto fromOne(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .url(product.getUrl())
                .countOfProducts(product.getCountOfProducts())
                .priceOfOne(product.getPriceOfOne())
                .categoryId(product.getCategories().getId())
                .build();
    }

    public static Product to(ProductDto productDto, Category category) {
        return Product.builder()
                .countOfProducts(productDto.getCountOfProducts())
                .priceOfOne(productDto.getPriceOfOne())
                .url(productDto.getUrl())
                .name(productDto.getName())
                .categories(category)
                .basketProducts(new ArrayList<>())
                .billProductList(new ArrayList<>()).build();
    }
}
