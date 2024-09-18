package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.WishlistRequest;
import com.example.back_end.dto.customer.response.WishlistResponse;

import java.util.List;

public interface WishListService {
        public WishlistResponse addToWishlist(Integer userId, WishlistRequest request);
        public List<WishlistResponse> getUserWishlist(Integer userId);
        public void removeFromWishlist(Integer userId, Integer productId);



}
