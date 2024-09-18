package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewResponse {
    private Integer id;
    private String comment;
    private Integer rating;
    private Integer productId;
    private Integer userId;
}
