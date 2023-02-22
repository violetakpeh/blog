package com.example.myProject9.repository;

import com.example.myProject9.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from categories where category_name = ?1",
            nativeQuery = true)
    Category findCategoryByCategory_name(String categoryName);
}
