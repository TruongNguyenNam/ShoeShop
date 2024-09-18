package com.example.back_end.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SupplierRequest {
    private Integer id;

    private String name;
    private String contactInfo;
}
