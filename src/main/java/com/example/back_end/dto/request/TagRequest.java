package com.example.back_end.dto.request;

import com.example.back_end.entity.ProductTag;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TagRequest {
    private String name;

    private List<ProductTag> productTagIds;
}
