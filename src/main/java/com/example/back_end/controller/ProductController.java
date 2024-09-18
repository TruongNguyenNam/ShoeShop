package com.example.back_end.controller;

import com.example.back_end.dto.request.ProductRequest;
import com.example.back_end.dto.response.ProductResponse;
import com.example.back_end.dto.response.ProductSearchResponse;
import com.example.back_end.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/product")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Thêm sản phẩm mới
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.addProduct(productRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer productId,
            @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Xóa sản phẩm
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    // Lấy tất cả sản phẩm
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    // Lấy sản phẩm theo ID
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer productId) {
        ProductResponse response = productService.getProductById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductSearchResponse>> searchByName(@RequestParam String name) {
        List<ProductSearchResponse> response = productService.searchByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
      //  return productService.searchByName(name);
    }

    // Tìm kiếm sản phẩm theo màu sắc
    @GetMapping("/search/color")
    public ResponseEntity<List<ProductSearchResponse>> searchByColor(@RequestParam(name = "color") String color) {
        List<ProductSearchResponse> response = productService.searchByColor(color);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search/brand")
    // Tìm kiếm sản phẩm theo thương hiệu
    public ResponseEntity<List<ProductSearchResponse> > searchByBrand(@RequestParam String brand) {
        List<ProductSearchResponse> response = productService.searchByBrand(brand);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search/category")
    // Tìm kiếm sản phẩm theo danh mục
    public ResponseEntity<List<ProductSearchResponse>> searchByCategory(@RequestParam String category) {
        List<ProductSearchResponse> response = productService.searchByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Tìm kiếm sản phẩm theo khoảng giá
    @GetMapping("/search/price")
    public ResponseEntity<List<ProductSearchResponse>> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<ProductSearchResponse> response = productService.searchByPriceRange(minPrice,maxPrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    //    @GetMapping("/search")
//    public List<ProductSearchResponse> searchProducts(@RequestBody ProductSearchRequest request) {
//        // Gọi các phương thức tìm kiếm dựa trên các tham số trong request
//        if (request.getName() != null) {
//            return productService.searchByName(request.getName());
//        } else if (request.getColor() != null) {
//            return productService.searchByColor(request.getColor());
//        } else if (request.getBrand() != null) {
//            return productService.searchByBrand(request.getBrand());
//        } else if (request.getCategory() != null) {
//            return productService.searchByCategory(request.getCategory());
//        } else if (request.getMinPrice() != null) {
//            return productService.searchByMinPrice(request.getMinPrice());
//        } else if (request.getMaxPrice() != null) {
//            return productService.searchByMaxPrice(request.getMaxPrice());
//        } else {
//            return List.of(); // Trả về danh sách rỗng nếu không có tiêu chí tìm kiếm
//        }
//    }

}
