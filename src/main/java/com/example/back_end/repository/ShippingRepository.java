package com.example.back_end.repository;


import com.example.back_end.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping,Integer> {

    Optional<Shipping> findByOrderId(Integer id);
}
