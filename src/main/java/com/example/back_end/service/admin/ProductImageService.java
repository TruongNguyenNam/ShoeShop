package com.example.back_end.service.admin;

import com.example.back_end.dto.response.ProductImageResponse;
import com.example.back_end.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {
    // Lưu danh sách ảnh sản phẩm và trả về danh sách các ID của ảnh đã lưu
    List<Integer> saveProductImage(List<MultipartFile> images, Integer productId);

    List<Integer> saveProductImage(List<MultipartFile> images);
    // Lấy chi tiết hình ảnh sản phẩm theo ID
    ProductImageResponse getPictureById(Integer id);
}
