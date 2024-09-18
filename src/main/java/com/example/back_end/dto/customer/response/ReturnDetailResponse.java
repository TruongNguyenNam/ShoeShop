package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
public class ReturnDetailResponse {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private BigDecimal refundAmount;
}
