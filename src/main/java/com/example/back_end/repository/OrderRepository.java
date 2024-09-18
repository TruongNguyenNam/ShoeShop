package com.example.back_end.repository;


import com.example.back_end.entity.Order;
import com.example.back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Integer> {
        List<Order> findByUser(User user);

        List<Order>  findByUserId(Integer id);
}
