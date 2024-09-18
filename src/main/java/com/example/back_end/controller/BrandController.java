package com.example.back_end.controller;

import com.example.back_end.dto.response.BrandResponse;
import com.example.back_end.service.admin.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getAllBrands() {
        List<BrandResponse> brands = brandService.getALL();
        return ResponseEntity.ok(brands);
    }
}
