package com.example.myProject9.service;

import com.example.myProject9.dto.CategoryDto;
import com.example.myProject9.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Long categoryId);

    ResponseEntity<Category> addCategory(Category category);

    List<Category> viewAllCategories();

    Category findCategoryByName(String categoryName);
}
