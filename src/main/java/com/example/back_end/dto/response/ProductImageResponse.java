package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductImageResponse {
    private Integer id;
    private String imageUrl;
    private Integer productId;
}
