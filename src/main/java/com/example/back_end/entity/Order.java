package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();

    private Double totalAmount;

    private String shippingAddress;

    private String paymentMethod;


    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    @OneToMany(mappedBy = "order")
    private List<Shipping> shippings;

    @OneToMany(mappedBy = "order")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "order")
    private List<Return> returns;
    @NoArgsConstructor
    @Getter
    public enum OrderStatus{
        OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }

}
