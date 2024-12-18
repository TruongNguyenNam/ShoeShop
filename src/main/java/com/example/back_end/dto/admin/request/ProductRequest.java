package com.example.back_end.dto.admin.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Integer categoryId;
    private Integer brandId;
    private Integer sizeId;
    private Integer colorId;
    private List<Integer> imageIds; // List of image IDs
    private List<Integer> tagIds; // List of tag IDs
    private List<Integer> campaignIds; // List of campaign IDs
    private List<Integer> inventoryIds; // List of inventory IDs
    private List<Integer> supplierIds;
}
