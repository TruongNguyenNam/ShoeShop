package com.example.back_end.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;


    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
