package com.example.back_end.dto.customer.response;

import com.example.back_end.entity.Order;
import com.example.back_end.entity.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionResponse {
    private Integer id;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private String status;


}
