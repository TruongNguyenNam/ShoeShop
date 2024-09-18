package com.example.back_end.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "Campaign")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Campaign extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


    @OneToMany(mappedBy = "campaign")
    private List<CampaignProduct> campaignProducts;
}
