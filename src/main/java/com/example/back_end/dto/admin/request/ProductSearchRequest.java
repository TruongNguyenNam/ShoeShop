package com.example.back_end.dto.admin.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSearchRequest {
    private String name;          // Tìm theo tên sản phẩm
    private String color;         // Tìm theo tên màu (Color)
    private String brand;         // Tìm theo tên thương hiệu (Brand)
    private String category;      // Tìm theo tên danh mục (Category)
    private Double minPrice;      // Tìm sản phẩm có giá >= minPrice
    private Double maxPrice;
}
