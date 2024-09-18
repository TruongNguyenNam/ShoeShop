package com.example.back_end.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ActivityLog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityLog extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String activity;

}
