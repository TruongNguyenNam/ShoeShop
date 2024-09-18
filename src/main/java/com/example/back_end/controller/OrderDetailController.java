//package com.example.back_end.controller;
//
//import com.example.back_end.dto.customer.request.OrderDetailRequest;
//import com.example.back_end.dto.customer.request.OrderRequest;
//import com.example.back_end.dto.customer.response.OrderDetailResponse;
//import com.example.back_end.service.customer.OrderDetailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/admin/orderDetail")
//@Validated
//public class OrderDetailController {
//    @Autowired
//    private OrderDetailService orderDetailService;
//
//    // Tạo chi tiết đơn hàng
//    @PostMapping
//    public ResponseEntity<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
//        OrderDetailResponse orderDetailResponse = orderDetailService.createOrderDetail(orderDetailRequest);
//        return new ResponseEntity<>(orderDetailResponse, HttpStatus.CREATED);
//    }
//
//    // Xem chi tiết đơn hàng theo ID đơn hàng
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<List<OrderDetailResponse>> viewOrderDetailsByOrder(@PathVariable Integer orderId) {
//        List<OrderDetailResponse> orderDetails = orderDetailService.viewOrderDetailsByOrder(orderId);
//        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
//    }
//}
