package com.example.back_end.controller;

import com.example.back_end.dto.customer.request.CartRequest;
import com.example.back_end.dto.customer.response.CartResponse;
import com.example.back_end.service.customer.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/cart")
@Validated
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) {
        CartResponse response = cartService.addToCart(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> viewCart(@PathVariable Integer userId) {
        List<CartResponse> cartResponses = cartService.viewCart(userId);
        return new ResponseEntity<>(cartResponses, HttpStatus.OK);
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<CartResponse> updateCartQuantity(@PathVariable Integer cartId, @RequestParam Integer newQuantity) {
        CartResponse response = cartService.updateCartQuantity(cartId, newQuantity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Integer cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
