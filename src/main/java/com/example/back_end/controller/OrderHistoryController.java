package com.example.back_end.controller;

import com.example.back_end.dto.customer.response.OrderHistoryResponse;
import com.example.back_end.service.customer.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/orderHistory")
public class OrderHistoryController {
    @Autowired
    private OrderHistoryService orderHistoryService;

    @GetMapping("/history")
    public ResponseEntity<List<OrderHistoryResponse>> getOrderHistory(@RequestParam Integer userId) {
        List<OrderHistoryResponse> orderHistory = orderHistoryService.getOrderHistory(userId);
        return ResponseEntity.ok(orderHistory);
    }



}
