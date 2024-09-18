package com.example.back_end.repository;


import com.example.back_end.dto.customer.response.WishlistResponse;
import com.example.back_end.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findByUserId(Integer id);

    void deleteByUserIdAndProductId(int userId, int productId);
}
