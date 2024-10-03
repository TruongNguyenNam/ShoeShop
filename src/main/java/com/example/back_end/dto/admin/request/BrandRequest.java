package com.example.back_end.dto.admin.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BrandRequest {
    private String name;
    private String description;
}
