package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "Address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String address;


}
