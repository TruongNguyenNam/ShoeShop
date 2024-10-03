package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.ColorResponse;

import java.util.List;

public interface ColorService {
    List<ColorResponse> getAll();
}
