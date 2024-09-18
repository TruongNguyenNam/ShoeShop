package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShippingResponse {
    private Long id;
    private Long orderId;
    private Long shippingMethodId;
    private String trackingNumber;
    private String carrier;
    private String status;
    private LocalDateTime shippingDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
