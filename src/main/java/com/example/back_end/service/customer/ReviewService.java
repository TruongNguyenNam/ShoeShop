package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.ReviewRequest;
import com.example.back_end.dto.customer.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

     ReviewResponse addReview(ReviewRequest request);


     List<ReviewResponse> getProductReviews(Integer productId);




}
