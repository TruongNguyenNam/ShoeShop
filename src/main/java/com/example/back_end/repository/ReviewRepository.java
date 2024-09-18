package com.example.back_end.repository;


import com.example.back_end.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

   List<Review> findByProductId(Integer productId);
}
