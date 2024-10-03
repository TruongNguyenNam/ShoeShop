package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.OrderHistoryResponse;
import com.example.back_end.entity.Order;
import com.example.back_end.entity.Shipping;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.OrderRepository;
import com.example.back_end.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderHistoryImpl implements OrderHistoryService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShippingRepository shippingRepository;
    @Override
    public List<OrderHistoryResponse> getOrderHistory(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
            if(orders.isEmpty()){
                throw new AppException("orders is not found");
            }
            return orders.stream().map(this::mapToResponse).collect(Collectors.toList());

        }

    private OrderHistoryResponse mapToResponse(Order order) {
        // Lấy thông tin vận chuyển dựa vào orderId
//        Shipping shipping = shippingRepository.findByOrderId(order.getId())
//                .orElseThrow(() -> new AppException("Shipping information not found for order ID: " + order.getId()));

        // Tạo đối tượng phản hồi với dữ liệu cần thiết
        OrderHistoryResponse response = new OrderHistoryResponse();
        response.setOrderId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(String.valueOf(order.getStatus()));
//        response.setShippingMethod(shipping.getShippingMethod().getName());
//        response.setTrackingNumber(shipping.getTrackingNumber());
//        response.setShippingStatus(String.valueOf(shipping.getStatus()));

        return response;
    }
}
