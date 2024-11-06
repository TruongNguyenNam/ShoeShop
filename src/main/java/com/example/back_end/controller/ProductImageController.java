package com.example.back_end.controller;

import com.example.back_end.dto.admin.response.ProductImageResponse;
import com.example.back_end.exception.AppException;
import com.example.back_end.service.admin.image.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/ProductImage")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;


    @PostMapping("/upload")
    public ResponseEntity<List<Integer>> uploadProductImages(@RequestParam("images") List<MultipartFile> images) {
        try {
            List<Integer> imageIds = productImageService.saveProductImage(images);
            return ResponseEntity.ok(imageIds);
        } catch (Exception e) {
            throw new AppException("Failed to upload images");
        }
    }

    // API để lấy chi tiết ảnh theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductImageResponse> getProductImageById(@PathVariable Integer id) {
        ProductImageResponse response = productImageService.getPictureById(id);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
