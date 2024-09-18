package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "CustomerService")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate = new Date();
    private String issue;

    @Enumerated(EnumType.STRING)
    private Status status;
    @NoArgsConstructor
    @Getter
    public enum Status {
       OPEN, IN_PROGRESS, RESOLVED, CLOSED
    }
}
