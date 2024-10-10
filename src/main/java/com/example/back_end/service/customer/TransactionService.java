package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.TransactionResponse;
import com.example.back_end.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getAll();



}
