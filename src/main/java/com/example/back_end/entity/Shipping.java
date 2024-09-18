package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Shipping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipping extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_method_id", nullable = false)
    private ShippingMethod shippingMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private Date shippingDate;
    private String trackingNumber;
    private String carrier;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ShippingStatus status;

    @NoArgsConstructor
    @Getter
    public enum ShippingStatus{
        SHIPPED, IN_TRANSIT,DELIVERED, RETURNED
    }
}
