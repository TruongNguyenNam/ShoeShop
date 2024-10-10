package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.ReviewRequest;
import com.example.back_end.dto.customer.response.ReviewResponse;
import com.example.back_end.entity.*;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewResponse addReview(ReviewRequest request) {
        Review review = new Review();
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() ->
                new AppException("product is not found"));

        User user = userRepository.findById(request.getUserId()).orElseThrow(() ->
                new AppException("user is not found"));
        review.setProduct(product);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setComment(review.getComment());
        reviewRepository.save(review);
        return mapToResponse(review);

    }

    @Override
    public List<ReviewResponse> getProductReviews(Integer productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
            if(reviews.isEmpty()){
                throw new AppException("reviews is not found");
            }
        return reviews.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private ReviewResponse mapToResponse(Review review){
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setProductId(review.getProduct().getId());
        response.setUserId(review.getUser().getId());
        response.setRating(review.getRating());
        response.setComment(response.getComment());
        return response;
    }


}
