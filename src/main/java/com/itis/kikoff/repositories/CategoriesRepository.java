package com.itis.kikoff.repositories;

import com.itis.kikoff.models.shop.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
