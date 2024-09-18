package com.example.back_end.repository;

import com.example.back_end.entity.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingMethodRepository extends JpaRepository<ShippingMethod,Integer> {
}
