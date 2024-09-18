package com.example.back_end.repository;

import com.example.back_end.entity.ProductImage;
import com.example.back_end.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    void deleteByProductId(Integer id);

    List<ProductImage> findByProductId(Integer productId);


}
