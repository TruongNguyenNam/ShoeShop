package com.example.back_end.repository;

import com.example.back_end.entity.Inventory;
import com.example.back_end.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {
   Optional<Inventory> findByProductId(Integer productId);
}
