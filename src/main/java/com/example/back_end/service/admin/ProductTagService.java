package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.ProductTagResponse;

import java.util.List;

public interface ProductTagService {
    List<ProductTagResponse> getAll();
}
