package com.example.back_end.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Tag")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ProductTag> productTags;
}
