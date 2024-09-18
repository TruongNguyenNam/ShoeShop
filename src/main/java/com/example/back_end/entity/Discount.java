package com.example.back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Discount")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String description;
    private Double discountPercentage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;

}
