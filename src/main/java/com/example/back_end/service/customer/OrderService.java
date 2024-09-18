package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.OrderRequest;
import com.example.back_end.dto.customer.response.OrderResponse;

import java.util.List;

public interface OrderService {
//    OrderResponse createOrder(OrderRequest request);
//    List<OrderResponse> viewOrdersByUser(Integer userId);
//    OrderResponse updateOrderStatus(Integer orderId, String status);
  OrderResponse placeOrder(Integer userId, OrderRequest request);
}
