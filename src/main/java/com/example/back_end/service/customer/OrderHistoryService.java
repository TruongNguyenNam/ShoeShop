package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.OrderHistoryResponse;

import java.util.List;

public interface OrderHistoryService {



 List<OrderHistoryResponse> getOrderHistory(Integer userId);



}
