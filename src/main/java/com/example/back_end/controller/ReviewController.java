package com.example.back_end.controller;

import com.example.back_end.dto.customer.request.ReviewRequest;
import com.example.back_end.dto.customer.response.ReviewResponse;
import com.example.back_end.service.customer.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/review")
@Validated
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewRequest request){
        ReviewResponse response = reviewService.addReview(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<List<ReviewResponse>> getProductReviews(@PathVariable(name = "productId") Integer productId){
        List<ReviewResponse> reviewResponses = reviewService.getProductReviews(productId);
        return new ResponseEntity<>(reviewResponses,HttpStatus.OK);
  }

}
