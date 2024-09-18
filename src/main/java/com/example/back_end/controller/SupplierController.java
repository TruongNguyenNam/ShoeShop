package com.example.back_end.controller;

import com.example.back_end.dto.response.SupplierResponse;
import com.example.back_end.service.admin.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        List<SupplierResponse> suppliers = supplierService.getAll();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
}
