package com.example.myProject9.service.implementation;

import com.example.myProject9.dto.CategoryDto;
import com.example.myProject9.exception.CategoryNotFoundException;
import com.example.myProject9.model.Category;
import com.example.myProject9.repository.CategoryRepository;
import com.example.myProject9.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("category not found"));
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
       //Category category = new Category();
       category.setCategory_name(category.getCategory_name());
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @Override
    public List<Category> viewAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByCategory_name(categoryName);

    }
}
