package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.ColorResponse;

import java.util.List;

public interface ColorService {
    List<ColorResponse> getAll();
}
