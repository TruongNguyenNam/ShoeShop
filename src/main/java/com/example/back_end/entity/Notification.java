package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;
    @Enumerated(EnumType.STRING)
    private status status;
    @NoArgsConstructor
    @Getter
    public enum status {
        UNREAD, READ
    }

}
