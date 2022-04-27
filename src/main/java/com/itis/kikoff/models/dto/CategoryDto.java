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
public class CategoryDto {
    private Long id;
    private String name;
    public static List<CategoryDto> from(List<Category> categories) {
        List<CategoryDto> result = new ArrayList<>();
        for (Category category : categories) {
            CategoryDto categoryDto = CategoryDto.builder()
                            .id(category.getId())
                                    .name(category.getName())
                                            .build();
            result.add(categoryDto);
        }
        return result;
    }
    public static Category to(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new ArrayList<>())
                .build();
    }
}
