package com.example.back_end.repository;


import com.example.back_end.entity.Category;
import com.example.back_end.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByProducts(List<Product> products);
}
