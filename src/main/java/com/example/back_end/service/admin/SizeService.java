package com.example.back_end.service.admin;

import com.example.back_end.dto.response.SizeResponse;
import com.example.back_end.entity.Size;

import java.util.List;

public interface SizeService {
    List<SizeResponse> getAll();
}
