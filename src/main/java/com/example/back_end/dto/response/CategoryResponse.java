package com.example.back_end.dto.response;

import com.example.back_end.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
    private List<ProductResponse> products;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
