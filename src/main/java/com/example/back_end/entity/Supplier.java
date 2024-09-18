package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "supplier")
    private List<ProductSupplier> productSuppliers;
}
