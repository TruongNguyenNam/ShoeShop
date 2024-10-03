package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierResponse> getAll();
}
