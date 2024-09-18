package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "CampaignProduct")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampaignProduct extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double discountPercentage;


}
