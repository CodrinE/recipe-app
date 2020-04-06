package com.springframework.recipeapp.domain.repositories;

import com.springframework.recipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
