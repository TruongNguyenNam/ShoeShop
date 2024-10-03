package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderResponse {
        private Integer id;
        private Integer userId;
        private String orderDate;
        private String status;
        private Double totalAmount;
        private String shippingAddress;
        private String paymentMethod;
        private List<OrderDetailResponse> orderDetails;
        private List<ShippingResponse> shippingResponses;



}
