package com.itis.kikoff.models.dto;

import com.itis.kikoff.models.shop.Category;
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
public class CategoryRespDto {
    private Long id;
    private String name;
    public static List<CategoryRespDto> from(List<Category> categories) {
        List<CategoryRespDto> result = new ArrayList<>();
        for (Category category : categories) {
            CategoryRespDto categoryDto = CategoryRespDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
            result.add(categoryDto);
        }
        return result;
    }
    public static Category to(CategoryRespDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new ArrayList<>())
                .build();
    }
}
