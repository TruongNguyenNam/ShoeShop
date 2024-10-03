package com.example.back_end.dto.customer.request;

import com.example.back_end.entity.Notification;
import com.example.back_end.entity.OrderDetail;
import com.example.back_end.entity.Shipping;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {
    private Integer userId;
    private String shippingAddress;
    private String paymentMethod;
    private List<OrderDetailRequest> orderDetails;
    private String promotionCode;
    private List<ShippingRequest> shippingRequests;
    private List<Integer> transactionIds;

}
