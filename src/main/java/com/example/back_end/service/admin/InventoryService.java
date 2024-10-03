package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> getALL();
}
