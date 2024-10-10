package com.example.back_end.controller;

import com.example.back_end.dto.customer.response.OrderHistoryResponse;
import com.example.back_end.service.customer.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/orderHistory")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<OrderHistoryResponse>> getOrderHistory(@PathVariable Integer userId) {
        List<OrderHistoryResponse> orderHistoryResponses = orderHistoryService.getOrderHistory(userId);
        return ResponseEntity.ok(orderHistoryResponses);
    }



}
