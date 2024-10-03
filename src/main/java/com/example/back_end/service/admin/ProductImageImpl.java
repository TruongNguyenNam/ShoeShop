package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.ProductImageResponse;
import com.example.back_end.entity.Product;
import com.example.back_end.entity.ProductImage;
import com.example.back_end.repository.ProductImageRepository;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.service.cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductImageImpl implements ProductImageService{
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Integer> saveProductImage(List<MultipartFile> images, Integer productId) {
        List<Integer> imageIds = new ArrayList<>();

        // Tìm product dựa trên productId
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        for (MultipartFile image : images) {
            try {
                // Tải ảnh lên Cloudinary
                Map<String, Object> uploadResult = cloudinaryService.uploadFile(image, "product_images");

                // Lấy URL ảnh từ Cloudinary
                String imageUrl = (String) uploadResult.get("url");

                // Tạo đối tượng ProductImage và lưu vào cơ sở dữ liệu
                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(imageUrl);
                productImage.setProduct(product);

                // Lưu vào CSDL
                ProductImage savedImage = productImageRepository.save(productImage);

                // Thêm ID ảnh vào danh sách trả về
                imageIds.add(savedImage.getId());
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý lỗi tải lên hoặc lưu ảnh
            }
        }



        return imageIds;
    }

    @Override
    public List<Integer> saveProductImage(List<MultipartFile> images) {
        return images.stream().map(image -> {
            try {
                // Tải ảnh lên Cloudinary
                Map<String, Object> uploadResult = cloudinaryService.uploadFile(image, "product_images");

                // Lấy URL ảnh từ Cloudinary
                String imageUrl = (String) uploadResult.get("url");

                // Tạo đối tượng ProductImage và lưu vào cơ sở dữ liệu
                ProductImage productImage = new ProductImage();
                productImage.setImageUrl(imageUrl);

                // Lưu vào CSDL
                ProductImage savedImage = productImageRepository.save(productImage);

                // Trả về ID ảnh đã lưu
                return savedImage.getId();
            } catch (IOException e) {
                e.printStackTrace();
                // Xử lý lỗi tải lên hoặc lưu ảnh và trả về null hoặc giá trị mặc định
                return null;
            }
        }).collect(Collectors.toList());
        //filter(Objects::nonNull).toList();
        // Loại bỏ null trong danh sách kết quả
    }


    @Override
    public ProductImageResponse getPictureById(Integer id) {
        // Lấy ảnh từ CSDL bằng ID
        ProductImage productImage = productImageRepository.findById(id).orElse(null);

        if (productImage != null) {
            ProductImageResponse response = new ProductImageResponse();
            response.setId(productImage.getId());
            response.setImageUrl(productImage.getImageUrl());
            response.setProductId(productImage.getProduct().getId());
            return response;
        }

        return null; // Xử lý trường hợp không tìm thấy ảnh
    }
}
