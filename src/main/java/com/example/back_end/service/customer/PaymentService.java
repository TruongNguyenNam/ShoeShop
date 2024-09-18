package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.PaymentRequest;
import com.example.back_end.dto.customer.response.PaymentResponse;
import com.example.back_end.repository.OrderRepository;
import com.example.back_end.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface PaymentService  {

    PaymentResponse processPayment(PaymentRequest request);




}
