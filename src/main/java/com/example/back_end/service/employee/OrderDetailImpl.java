//package com.example.back_end.service.customer;
//
//import com.example.back_end.dto.customer.request.OrderDetailRequest;
//import com.example.back_end.dto.customer.response.OrderDetailResponse;
//import com.example.back_end.entity.Order;
//import com.example.back_end.entity.OrderDetail;
//import com.example.back_end.entity.Product;
//import com.example.back_end.exception.AppException;
//import com.example.back_end.repository.OrderDetailRepository;
//import com.example.back_end.repository.OrderRepository;
//import com.example.back_end.repository.ProductRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class OrderDetailImpl implements OrderDetailService{
//
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    public OrderDetailResponse createOrderDetail(OrderDetailRequest request) {
//        Order order = orderRepository.findById(request.getOrderId())
//                .orElseThrow(() -> new AppException("Order not found with ID: " + request.getOrderId()));
//
//        Product product = productRepository.findById(request.getProductId())
//                .orElseThrow(() -> new AppException("Product not found with ID: " + request.getProductId()));
//
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setProduct(product);
//        orderDetail.setPrice(request.getPrice());
//        orderDetail.setQuantity(request.getQuantity());
//        orderDetail.setTotal(request.getTotal());
//
//        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
//
//        // Cập nhật tổng tiền cho đơn hàng
//        updateOrderTotalAmount(order.getId());
//
//        return mapToOrderDetailResponse(savedOrderDetail);
//    }
//
//    private void updateOrderTotalAmount(Integer orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new AppException("Order not found with ID: " + orderId));
//
//        double totalAmount = orderDetailRepository.findByOrder(order).stream()
//                .mapToDouble(OrderDetail::getTotal)
//                .sum();
//
//        order.setTotalAmount(totalAmount);
//        orderRepository.save(order);
//    }
//
//    public List<OrderDetailResponse> viewOrderDetailsByOrder(Integer orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new AppException("Order not found with ID: " + orderId));
//
//        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
//        return orderDetails.stream().map(this::mapToOrderDetailResponse).collect(Collectors.toList());
//    }
//
//    private OrderDetailResponse mapToOrderDetailResponse(OrderDetail orderDetail) {
//        OrderDetailResponse response = new OrderDetailResponse();
//        response.setId(orderDetail.getId());
//        response.setOrderId(orderDetail.getOrder().getId());
//        response.setProductId(orderDetail.getProduct().getId());
//        response.setProductName(orderDetail.getProduct().getName());
//        response.setPrice(orderDetail.getPrice());
//        response.setQuantity(orderDetail.getQuantity());
//        response.setTotal(orderDetail.getTotal());
//        return response;
//    }
//}
//
