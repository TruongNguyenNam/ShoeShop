package com.example.back_end.controller;

import com.example.back_end.dto.customer.request.OrderRequest;
import com.example.back_end.dto.customer.response.OrderResponse;
import com.example.back_end.service.customer.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/order")
@Validated
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<OrderResponse> placeOrder(@PathVariable Integer userId, @RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.placeOrder(userId, request);
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }


}
