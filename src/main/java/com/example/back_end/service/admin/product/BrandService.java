package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.BrandResponse;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getALL();
}
