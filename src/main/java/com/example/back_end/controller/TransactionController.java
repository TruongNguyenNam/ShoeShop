package com.example.back_end.controller;


import com.example.back_end.dto.admin.response.BrandResponse;
import com.example.back_end.dto.customer.response.TransactionResponse;
import com.example.back_end.service.customer.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAll() {
        List<TransactionResponse> transactionResponses = transactionService.getAll();
        return ResponseEntity.ok(transactionResponses);
    }




}
