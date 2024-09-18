package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShippingResponse {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private String carrier;
    private LocalDateTime shippingDate;
    private String status;
    private String trackingNumber;
    private Integer orderId;
    private Integer shippingMethodId;


}
