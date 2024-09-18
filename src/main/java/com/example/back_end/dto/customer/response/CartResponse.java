package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CartResponse {
    private Integer id;
    private Integer quantity;
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer userId;
    private Double totalPrice;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;


}
