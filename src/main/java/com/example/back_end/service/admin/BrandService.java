package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.BrandResponse;

import java.util.List;

public interface BrandService {
    List<BrandResponse> getALL();
}
