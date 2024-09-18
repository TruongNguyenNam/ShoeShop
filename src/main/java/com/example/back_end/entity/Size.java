package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Size extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String size;


    @OneToMany(mappedBy = "size")
    private List<Product> products;
}
