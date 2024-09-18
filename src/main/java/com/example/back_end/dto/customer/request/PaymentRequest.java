package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private Double amount;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
    private Integer orderId;


}
