package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Return")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Return extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate = new Date();
    private String reason;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReturnStatus status;

    @OneToMany(mappedBy = "returnOrder", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ReturnDetail> returnDetails;

    @NoArgsConstructor
    @Getter
    public enum ReturnStatus{
        PENDING, APPROVED, REJECTED

    }
}
