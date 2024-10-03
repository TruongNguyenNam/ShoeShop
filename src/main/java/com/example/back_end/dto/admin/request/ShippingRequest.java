package com.example.back_end.dto.admin.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShippingRequest {
    private Long orderId;
    private Long shippingMethodId;
    private String trackingNumber;
    private String carrier;
    private String status;
}
