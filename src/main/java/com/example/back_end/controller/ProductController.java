package com.example.back_end.controller;

import com.example.back_end.dto.admin.request.ProductRequest;
import com.example.back_end.dto.admin.response.ProductResponse;
import com.example.back_end.dto.admin.response.ProductSearchResponse;
import com.example.back_end.service.admin.product.ProductService;
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

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Integer productId,
            @RequestBody ProductRequest productRequest) {
        ProductResponse response = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response = productService.getAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer productId) {
        ProductResponse response = productService.getProductById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductSearchResponse>> searchByName(@RequestParam String name) {
        List<ProductSearchResponse> response = productService.searchByName(name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search/color")
    public ResponseEntity<List<ProductSearchResponse>> searchByColor(@RequestParam(name = "color") String color) {
        List<ProductSearchResponse> response = productService.searchByColor(color);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search/brand")
    public ResponseEntity<List<ProductSearchResponse> > searchByBrand(@RequestParam String brand) {
        List<ProductSearchResponse> response = productService.searchByBrand(brand);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search/category")
    public ResponseEntity<List<ProductSearchResponse>> searchByCategory(@RequestParam String category) {
        List<ProductSearchResponse> response = productService.searchByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search/price")
    public ResponseEntity<List<ProductSearchResponse>> searchByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<ProductSearchResponse> response = productService.searchByPriceRange(minPrice,maxPrice);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
