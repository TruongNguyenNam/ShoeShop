package com.example.back_end.dto.admin.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductImageRequest {
    private Integer productId;
    private String imageUrl;
}
