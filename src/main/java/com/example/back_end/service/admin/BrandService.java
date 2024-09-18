package com.example.back_end.service.admin;

import com.example.back_end.dto.response.BrandResponse;
import com.example.back_end.entity.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getALL();
}
