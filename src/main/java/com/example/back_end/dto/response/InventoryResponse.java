package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoryResponse {
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer stock;
}
