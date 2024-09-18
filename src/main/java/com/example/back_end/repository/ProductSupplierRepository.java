package com.example.back_end.repository;


import com.example.back_end.entity.ProductSupplier;
import com.example.back_end.entity.ProductTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Integer> {
    void deleteByProductId(Integer id);

    List<ProductSupplier> findByProductId(Integer productId);
}
