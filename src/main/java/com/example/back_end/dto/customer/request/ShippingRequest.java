package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class ShippingRequest {
    private String carrier;
    private Date shippingDate;
    private String status;
    private String trackingNumber;
    private Integer shippingMethodId;
}
