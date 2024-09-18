package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ProductImage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String imageUrl;

}
