package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSupplierResponse {
    private Integer id;
    private String supplierName;
}
