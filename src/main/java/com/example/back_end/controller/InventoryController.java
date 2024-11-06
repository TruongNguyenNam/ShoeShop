package com.example.back_end.controller;

import com.example.back_end.dto.admin.response.InventoryResponse;
import com.example.back_end.service.admin.product.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventories() {
        List<InventoryResponse> inventories = inventoryService.getALL();
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }

}
