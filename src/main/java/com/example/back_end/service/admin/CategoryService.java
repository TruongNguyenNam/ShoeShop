package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.request.CategoryRequest;
import com.example.back_end.dto.admin.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse saveOrUpdateCategory(Integer categoryId, CategoryRequest categoryRequestDTO);

    void deleteCategory(Integer categoryId);

    CategoryResponse getCategoryById(Integer categoryId);

    List<CategoryResponse> getAllCategories();


}
