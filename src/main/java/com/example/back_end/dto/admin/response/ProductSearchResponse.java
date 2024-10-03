package com.example.back_end.dto.admin.response;

import com.example.back_end.entity.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductSearchResponse {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String categoryName;
    private String brandName;
    private String size;
    private String color;
    private List<String> imageUrls; // List of image URLs


}
