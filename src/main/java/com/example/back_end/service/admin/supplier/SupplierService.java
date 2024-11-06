package com.example.back_end.service.admin.supplier;

import com.example.back_end.dto.admin.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierResponse> getAll();
}
