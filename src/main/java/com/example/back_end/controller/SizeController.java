package com.example.back_end.controller;

import com.example.back_end.dto.response.SizeResponse;
import com.example.back_end.service.admin.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping
    public ResponseEntity<List<SizeResponse>> getAllSizes() {
        List<SizeResponse> sizes = sizeService.getAll();
        return ResponseEntity.ok(sizes);
    }
}
