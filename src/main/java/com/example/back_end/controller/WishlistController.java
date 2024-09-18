package com.example.back_end.controller;

import com.example.back_end.dto.customer.request.WishlistRequest;
import com.example.back_end.dto.customer.response.WishlistResponse;
import com.example.back_end.service.customer.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/wishList")
public class WishlistController {
    @Autowired
    private WishListService wishListService;

    @PostMapping("/{userId}")
    public ResponseEntity<WishlistResponse> addToWishlist(@PathVariable Integer userId, @RequestBody WishlistRequest request) {
        WishlistResponse response = wishListService.addToWishlist(userId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistResponse>> getUserWishlist(@PathVariable Integer userId) {
        List<WishlistResponse> responses = wishListService.getUserWishlist(userId);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{productId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Integer userId, @PathVariable Integer productId) {
        wishListService.removeFromWishlist(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
