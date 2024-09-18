package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequest {

    private String comment;
    private Integer rating;
    private Integer productId;
    private Integer userId;


}
