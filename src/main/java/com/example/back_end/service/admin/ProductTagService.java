package com.example.back_end.service.admin;

import com.example.back_end.dto.response.ProductTagResponse;

import java.util.List;

public interface ProductTagService {
    List<ProductTagResponse> getAll();
}
