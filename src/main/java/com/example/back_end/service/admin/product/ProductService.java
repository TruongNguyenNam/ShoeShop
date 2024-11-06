package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.request.ProductRequest;
import com.example.back_end.dto.admin.request.ProductSearchRequest;
import com.example.back_end.dto.admin.response.ProductResponse;
import com.example.back_end.dto.admin.response.ProductSearchResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProduct(Integer productId);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Integer productId);

    List<ProductSearchResponse> searchByName(String name);
    List<ProductSearchResponse> searchByColor(String color);

    List<ProductSearchResponse> searchByBrand(String brand);

    // Tìm kiếm sản phẩm theo danh mục
     List<ProductSearchResponse> searchByCategory(String category);

    // Tìm kiếm sản phẩm theo giá tối thiểu
    List<ProductSearchResponse> searchByPriceRange(Double minPrice, Double maxPrice);

    List<ProductSearchResponse> searchProducts(ProductSearchRequest request);
}
