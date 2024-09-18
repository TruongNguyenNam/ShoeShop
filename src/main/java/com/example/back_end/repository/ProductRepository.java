package com.example.back_end.repository;


import com.example.back_end.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByColor_ColorIgnoreCase(String color);
//    findByColor_ColorIgnoreCase:
//    Color là tên của thuộc tính liên kết trong thực thể Product, đại diện cho đối tượng Color.
//    color là thuộc tính trong thực thể Color, dùng để lưu màu sắc.
    List<Product> findByBrand_NameIgnoreCase(String brandName);
    //    ở đây findByBrand đang liên keets vs bảng brand, ở đằng sau đại diênj thuộc tính có trong brand
    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

   // List<Product> findByCategory_Description(String key);

}
