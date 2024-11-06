package com.example.back_end.service.admin.image;

import com.example.back_end.dto.admin.response.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    List<Integer> saveProductImage(List<MultipartFile> images, Integer productId);

    List<Integer> saveProductImage(List<MultipartFile> images);
    ProductImageResponse getPictureById(Integer id);
}
