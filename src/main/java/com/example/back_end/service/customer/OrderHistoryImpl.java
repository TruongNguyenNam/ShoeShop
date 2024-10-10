package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.OrderDetailResponse;
import com.example.back_end.dto.customer.response.OrderHistoryResponse;
import com.example.back_end.dto.customer.response.ShippingResponse;
import com.example.back_end.entity.Order;
import com.example.back_end.entity.OrderDetail;
import com.example.back_end.entity.Shipping;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.OrderDetailRepository;
import com.example.back_end.repository.OrderRepository;
import com.example.back_end.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryImpl implements OrderHistoryService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public List<OrderHistoryResponse> getOrderHistory(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);

        List<OrderHistoryResponse> orderHistoryResponses = new ArrayList<>();
        for (Order order : orders) {
            OrderHistoryResponse orderHistoryResponse = mapToOrderHistoryResponse(order);
            orderHistoryResponses.add(orderHistoryResponse);
        }

        return orderHistoryResponses;
    }

    // Phương thức riêng để map từ Order sang OrderHistoryResponse
    private OrderHistoryResponse mapToOrderHistoryResponse(Order order) {
        OrderHistoryResponse response = new OrderHistoryResponse();
        response.setOrderId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus().name());
        response.setPaymentMethod(order.getPaymentMethod());

        // Gọi các phương thức riêng để lấy chi tiết đơn hàng và thông tin vận chuyển
        response.setOrderDetails(getOrderDetailResponses(order.getId()));
        response.setShippings(getShippingResponses(order.getId()));

        return response;
    }

    // Phương thức riêng để lấy danh sách chi tiết đơn hàng
    private List<OrderDetailResponse> getOrderDetailResponses(Integer orderId) {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailResponse detailResponse = new OrderDetailResponse();
            detailResponse.setProductId(orderDetail.getProduct().getId());
            detailResponse.setProductName(orderDetail.getProduct().getName());
            detailResponse.setQuantity(orderDetail.getQuantity());
            detailResponse.setPrice(orderDetail.getPrice());
            detailResponse.setTotal(orderDetail.getTotal());
            orderDetailResponses.add(detailResponse);
        }
        return orderDetailResponses;
    }

    // Phương thức riêng để lấy thông tin vận chuyển
    private List<ShippingResponse> getShippingResponses(Integer orderId) {
        List<Shipping> shippings = shippingRepository.findByOrderId(orderId);
        List<ShippingResponse> shippingResponses = new ArrayList<>();

        for (Shipping shipping : shippings) {
            ShippingResponse shippingResponse = new ShippingResponse();
            shippingResponse.setCarrier(shipping.getCarrier());
            shippingResponse.setShippingDate(shipping.getShippingDate());
            shippingResponse.setStatus(String.valueOf(shipping.getStatus()));
            shippingResponse.setTrackingNumber(shipping.getTrackingNumber());
            shippingResponses.add(shippingResponse);
        }
        return shippingResponses;
    }
}
