package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrandResponse {
    private Integer id;
    private String name;
    private String description;
}
