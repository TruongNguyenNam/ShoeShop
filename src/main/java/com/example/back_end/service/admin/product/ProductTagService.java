package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.ProductTagResponse;

import java.util.List;

public interface ProductTagService {
    List<ProductTagResponse> getAll();
}
