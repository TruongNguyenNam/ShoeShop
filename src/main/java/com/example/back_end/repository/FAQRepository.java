package com.example.back_end.repository;

import com.example.back_end.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FAQRepository extends JpaRepository<FAQ,Integer> {
}
