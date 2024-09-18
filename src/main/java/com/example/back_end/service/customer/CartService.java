package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.CartRequest;
import com.example.back_end.dto.customer.response.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    List<CartResponse> viewCart(Integer id);

   CartResponse updateCartQuantity(Integer id, Integer newQuantity);

    void removeFromCart(Integer id);

}
