package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderDetailResponse {
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double total;


}
