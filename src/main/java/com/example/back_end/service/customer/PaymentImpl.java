package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.PaymentRequest;
import com.example.back_end.dto.customer.response.PaymentResponse;
import com.example.back_end.entity.Order;
import com.example.back_end.entity.Payment;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.OrderRepository;
import com.example.back_end.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class PaymentImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException("Order not found"));

        // Xử lý thanh toán
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(Payment.PaymentStatus.valueOf("SUCCESS"));
        paymentRepository.save(payment);

        // Cập nhật trạng thái đơn hàng
        order.setStatus(Order.OrderStatus.valueOf("RESOLVED"));
        orderRepository.save(order);

        return convertToDTO(payment);
    }



    private PaymentResponse convertToDTO(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getId());

        Order order = orderRepository.findById(payment.getOrder().getId())
                .orElseThrow(() -> new AppException("order is not found"));

        response.setOrderId(order.getId());
        response.setPaymentDate(payment.getPaymentDate());
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setStatus(String.valueOf(payment.getStatus()));

        return response;
    }
}
