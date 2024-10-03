package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.SizeResponse;

import java.util.List;

public interface SizeService {
    List<SizeResponse> getAll();
}
