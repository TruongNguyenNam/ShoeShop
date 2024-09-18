package com.example.back_end.repository;


import com.example.back_end.entity.Cart;
import com.example.back_end.entity.Product;
import com.example.back_end.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);

    List<Cart> findByUserId(Integer id);

    void deleteByUserIdAndProductId(Integer userId, Integer productId);

}
