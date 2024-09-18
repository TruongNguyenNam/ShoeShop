package com.example.back_end.repository;



import com.example.back_end.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PromotionRepository extends JpaRepository<Promotion, Integer> {

    Optional<Promotion> findByCode(String code);
}
