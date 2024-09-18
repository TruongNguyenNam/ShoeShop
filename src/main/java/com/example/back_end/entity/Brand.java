package com.example.back_end.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "Brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {  //4 cái
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;


}
