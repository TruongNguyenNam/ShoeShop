package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderHistoryResponse {
    private Integer orderId;
    private Date orderDate;
    private Double totalAmount;
    private String status;
    private String paymentMethod;
    private List<OrderDetailResponse> orderDetails;
    private List<ShippingResponse> shippings;
}
