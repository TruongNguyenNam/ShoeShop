package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.request.CategoryRequest;
import com.example.back_end.dto.admin.response.CategoryResponse;
import com.example.back_end.dto.admin.response.ProductResponse;
import com.example.back_end.entity.Category;
import com.example.back_end.entity.Product;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional //
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

//    @Autowired
//    private ProductRepository productRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse saveOrUpdateCategory(Integer categoryId, CategoryRequest categoryRequest) {
        Category category;

        if (categoryId != null) {
            // Nếu có categoryId thì thực hiện cập nhật
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new AppException("Category not found with id: " + categoryId));
            category.setLastModifiedDate(LocalDateTime.now());
        } else {
            // Nếu không có categoryId thì thực hiện tạo mới
            category = new Category();
            category.setCreatedDate(LocalDateTime.now());
            category.setLastModifiedDate(LocalDateTime.now());
        }

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category savedCategory = categoryRepository.save(category);

        return mapToResponseDTO(savedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException("Category not found with id: " + categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException("Category not found with id: " + categoryId));

        return mapToResponseDTO(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        return categories.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private CategoryResponse mapToResponseDTO(Category category) {
        CategoryResponse responseDTO = new CategoryResponse();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());

        // Kiểm tra nếu danh sách products không null trước khi sử dụng stream()
        List<ProductResponse> productResponses = (category.getProducts() != null) ?
                category.getProducts().stream()
                        .map(this::mapToResponseDTO)  // Map từng Product entity sang ProductResponse
                        .collect(Collectors.toList()) : new ArrayList<>();

        responseDTO.setProducts(productResponses);
        responseDTO.setDescription(category.getDescription());
        responseDTO.setCreatedDate(category.getCreatedDate());
        responseDTO.setLastModifiedDate(category.getLastModifiedDate());
        return responseDTO;
    }


    private ProductResponse mapToResponseDTO(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        // Assuming Product has a reference to Category and Brand
        productResponse.setCategoryName(product.getCategory().getName());
        productResponse.setBrandName(product.getBrand().getName());

        // Assuming Product has size and color fields
        productResponse.setSize(product.getSize().getSize());
        productResponse.setColor(product.getColor().getColor());

        // Assuming Product has a list of image URLs, tags, campaigns, inventories, and suppliers
        productResponse.setImageUrls(product.getImages().stream()
                .map(image -> image.getImageUrl())  // Assuming each Image entity has a URL field
                .collect(Collectors.toList()));

        productResponse.setTagNames(product.getTags().stream()
                .map(tag -> tag.getTag().getName())  // Assuming each Tag entity has a name field
                .collect(Collectors.toList()));

        return productResponse;
    }

}
