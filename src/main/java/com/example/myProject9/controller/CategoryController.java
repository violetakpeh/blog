package com.example.myProject9.controller;

import com.example.myProject9.dto.CategoryDto;
import com.example.myProject9.model.Category;
import com.example.myProject9.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
    @GetMapping
    public List<Category> viewAllCategories() {
        return categoryService.viewAllCategories();
    }
}
