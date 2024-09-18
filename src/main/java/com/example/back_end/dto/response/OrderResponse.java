package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private String status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private LocalDateTime orderDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
