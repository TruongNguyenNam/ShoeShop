package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getALL();
}
