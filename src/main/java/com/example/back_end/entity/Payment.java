package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate = new Date();
    private Double amount;
    private String paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;


    @NoArgsConstructor
    @Getter
    public enum PaymentStatus{
        SUCCESS, FAILED, PENDING
    }

}
