package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class ProductResponse {
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
    private List<String> tagNames; // List of tag names
    private List<String> campaignNames; // List of campaign names
    private List<InventoryResponse> inventories; // List of inventory details
    private List<ProductSupplierResponse> suppliers; // List of supplier details
    // cái này trả
}
