package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Color")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Color extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String color;


    @OneToMany(mappedBy = "color")
    private List<Product> products;
}
