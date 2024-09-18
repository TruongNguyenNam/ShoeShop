package com.example.back_end.repository;

import com.example.back_end.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepository extends JpaRepository<CustomerService,Integer> {
}
