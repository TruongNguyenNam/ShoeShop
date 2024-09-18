package com.example.back_end.repository;

import com.example.back_end.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTagRepository extends JpaRepository<ProductTag,Integer> {
    void deleteByProductId(Integer id);
    List<ProductTag> findByProductId(Integer productId);
}
