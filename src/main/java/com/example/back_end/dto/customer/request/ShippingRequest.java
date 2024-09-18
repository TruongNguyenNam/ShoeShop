package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShippingRequest {
    private String carrier;
    private LocalDateTime shippingDate;
    private String status;
    private String trackingNumber;
    private Integer orderId;
    private Integer shippingMethodId;

}
