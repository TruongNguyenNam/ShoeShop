package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.TransactionResponse;
import com.example.back_end.entity.Transaction;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<TransactionResponse> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        if(transactions.isEmpty()){
            throw new AppException("transaction is not empty");
        }
        return transactions.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private TransactionResponse mapToResponse(Transaction transaction){
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setStatus(String.valueOf(transaction.getStatus()));
        response.setAmount(transaction.getAmount());
        response.setTransactionDate(transaction.getTransactionDate());
        return response;
    }


}
