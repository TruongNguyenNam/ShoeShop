package com.example.back_end.service.admin;

import com.example.back_end.dto.request.ProductRequest;
import com.example.back_end.dto.request.ProductSearchRequest;
import com.example.back_end.dto.response.InventoryResponse;
import com.example.back_end.dto.response.ProductResponse;
import com.example.back_end.dto.response.ProductSearchResponse;
import com.example.back_end.dto.response.ProductSupplierResponse;
import com.example.back_end.entity.*;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.*;
import com.example.back_end.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ProductImageRepository productImageRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductSupplierRepository productSupplierRepository;
    private final ProductTagRepository productTagRepository;
    private final TagRepository tagRepository;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
                          BrandRepository brandRepository, SizeRepository sizeRepository,
                          ColorRepository colorRepository, ProductImageRepository productImageRepository,
                          InventoryRepository inventoryRepository, ProductSupplierRepository productSupplierRepository,
                          ProductTagRepository productTagRepository, TagRepository tagRepository,SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.sizeRepository = sizeRepository;
        this.colorRepository = colorRepository;
        this.productImageRepository = productImageRepository;
        this.inventoryRepository = inventoryRepository;
        this.productSupplierRepository = productSupplierRepository;
        this.productTagRepository = productTagRepository;
        this.tagRepository = tagRepository;
        this.supplierRepository = supplierRepository;
    }

    // Thêm sản phẩm mới
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        // Set thông tin sản phẩm từ request
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());

        // Set các thuộc tính khác như Category, Brand, Size, Color
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found")));
        product.setBrand(brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new AppException("Brand not found")));
        product.setSize(sizeRepository.findById(productRequest.getSizeId())
                .orElseThrow(() -> new AppException("Size not found")));
        product.setColor(colorRepository.findById(productRequest.getColorId())
                .orElseThrow(() -> new AppException("Color not found")));

        // Lưu sản phẩm vào database
        Product savedProduct = productRepository.save(product); // lưu vài database trc

        // Lưu hình ảnh sản phẩm
        if (productRequest.getImageIds() != null && !productRequest.getImageIds().isEmpty()) {
            for (Integer imageUrl : productRequest.getImageIds()) {
                ProductImage image = productImageRepository.findById(imageUrl)
                        .orElseThrow(() -> new AppException("Image not found"));
                image.setProduct(savedProduct);
                productImageRepository.save(image);
            }
        }


        // Lưu thông tin nhà cung cấp
        if (productRequest.getSupplierIds() != null && !productRequest.getSupplierIds().isEmpty()) {
            for (Integer supplierId : productRequest.getSupplierIds()) {
                Supplier supplier = supplierRepository.findById(supplierId)
                        .orElseThrow(() -> new AppException("Supplier not found"));

                ProductSupplier productSupplier = new ProductSupplier();
                productSupplier.setProduct(savedProduct);
                productSupplier.setSupplier(supplier);
                productSupplierRepository.save(productSupplier);
            }
        }

        // Lưu thông tin tag sản phẩm (ProductTag)
        if (productRequest.getTagIds() != null && !productRequest.getTagIds().isEmpty()) {
            for (Integer tagId : productRequest.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new AppException("Tag not found"));
                ProductTag productTag = new ProductTag();
                productTag.setProduct(savedProduct);
                productTag.setTag(tag);
                productTagRepository.save(productTag);
            }
        }


        // Cập nhật thông tin tồn kho
        // Lưu hoặc cập nhật thông tin tồn kho
        Inventory inventory = inventoryRepository.findByProductId(savedProduct.getId())
                .orElse(new Inventory()); // Tìm tồn kho, nếu không tìm thấy thì tạo mới

        inventory.setProduct(savedProduct);
        inventory.setStock(productRequest.getStock());
        inventory.setReservedStock(16);
        inventory.setLastUpdated(LocalDateTime.now());

// Nếu tồn kho mới thì tạo mới, còn nếu đã có thì cập nhật
        if (inventory.getId() == null) {
            inventoryRepository.save(inventory);
        } else {
            inventoryRepository.save(inventory);
        }


        // Trả về ProductResponse
        return mapToProductResponse(savedProduct);
    }

    // Sửa thông tin sản phẩm
    public ProductResponse updateProduct(Integer productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException("Product not found"));

        // Update thông tin sản phẩm
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStock(productRequest.getStock());

        // Update các thuộc tính khác như Category, Brand, Size, Color
        product.setCategory(categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new AppException("Category not found")));
        product.setBrand(brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new AppException("Brand not found")));
        product.setSize(sizeRepository.findById(productRequest.getSizeId())
                .orElseThrow(() -> new AppException("Size not found")));
        product.setColor(colorRepository.findById(productRequest.getColorId())
                .orElseThrow(() -> new AppException("Color not found")));

        // Cập nhật hình ảnh sản phẩm
        productImageRepository.deleteByProductId(productId); // Xóa ảnh cũ trước khi thêm mới
        if (productRequest.getImageIds() != null && !productRequest.getImageIds().isEmpty()) {
            for (Integer imageUrl : productRequest.getImageIds()) {
                ProductImage image = new ProductImage();
                image.setProduct(product);
                image.setId(imageUrl);
                productImageRepository.save(image);
            }
        }

        // Cập nhật thông tin nhà cung cấp
        productSupplierRepository.deleteByProductId(productId); // Remove old suppliers
        if (productRequest.getSupplierIds() != null && !productRequest.getSupplierIds().isEmpty()) {
            for (Integer supplierId : productRequest.getSupplierIds()) {
                Supplier supplier = supplierRepository.findById(supplierId)
                        .orElseThrow(() -> new AppException("Supplier not found"));
                ProductSupplier productSupplier = new ProductSupplier();
                productSupplier.setProduct(product);
                productSupplier.setSupplier(supplier);
                productSupplierRepository.save(productSupplier);
            }
        }

        // Cập nhật thông tin tag sản phẩm
        productTagRepository.deleteByProductId(productId); // Xóa tag cũ
        if (productRequest.getTagIds() != null && !productRequest.getTagIds().isEmpty()) {
            for (Integer tagId : productRequest.getTagIds()) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new AppException("Tag not found"));
                ProductTag productTag = new ProductTag();
                productTag.setProduct(product);
                productTag.setTag(tag);
                productTagRepository.save(productTag);
            }
        }

        // Cập nhật thông tin tồn kho
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new AppException("Inventory not found"));
        inventory.setStock(productRequest.getStock());
        inventoryRepository.save(inventory);

        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }

    // Xóa sản phẩm
    public void deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException("Product not found"));
        productRepository.delete(product);
    }

    // Lấy tất cả sản phẩm
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    // Lấy sản phẩm theo ID
    public ProductResponse getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException("Product not found"));
        return mapToProductResponse(product);
    }

    public List<ProductSearchResponse> searchByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        if(products.isEmpty()){
            throw  new AppException("product not found");
        }
        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }

    // Tìm kiếm sản phẩm theo màu sắc
    public List<ProductSearchResponse> searchByColor(String color) {
        List<Product> products = productRepository.findByColor_ColorIgnoreCase(color);
        if(products.isEmpty()){
            throw  new AppException("color not found");
        }
        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }

    // Tìm kiếm sản phẩm theo thương hiệu
    public List<ProductSearchResponse> searchByBrand(String brand) {
        List<Product> products = productRepository.findByBrand_NameIgnoreCase(brand);
        if(products.isEmpty()){
            throw  new AppException("brand not found");
        }
        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }

    // Tìm kiếm sản phẩm theo danh mục
    public List<ProductSearchResponse> searchByCategory(String category) {
        List<Product> products = productRepository.findByCategory_NameIgnoreCase(category);
        if(products.isEmpty()){
            throw  new AppException("category not found");
        }
        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductSearchResponse> searchByPriceRange(Double minPrice, Double maxPrice) {
        List<Product> products = productRepository.findByPriceBetween(minPrice, maxPrice);
        if(minPrice > maxPrice){
            throw new AppException("Price not valid");
        } else if (products.isEmpty()) {
            throw new AppException("not found in approx");
        }
        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductSearchResponse> searchProducts(ProductSearchRequest request) {
        List<Product> products = productRepository.findAll();

        // Tìm kiếm theo tên
        if (request.getName() != null) {
            products = products.stream()
                    .filter(product -> product.getName().toLowerCase().contains(request.getName().toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo màu sắc
        if (request.getColor() != null) {
            products = products.stream()
                    .filter(product -> product.getColor().getColor().equalsIgnoreCase(request.getColor()))
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo thương hiệu
        if (request.getBrand() != null) {
            products = products.stream()
                    .filter(product -> product.getBrand().getName().contains(request.getBrand()))
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo danh mục
        if (request.getCategory() != null) {
            products = products.stream()
                    .filter(product -> product.getCategory().getName().contains(request.getCategory()))
                    .collect(Collectors.toList());
        }

        // Tìm kiếm theo khoảng giá
        if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            products = products.stream()
                    .filter(product -> product.getPrice() >= request.getMinPrice() && product.getPrice() <= request.getMaxPrice())
                    .collect(Collectors.toList());
        } else if (request.getMinPrice() != null) {
            products = products.stream()
                    .filter(product -> product.getPrice() >= request.getMinPrice())
                    .collect(Collectors.toList());
        } else if (request.getMaxPrice() != null) {
            products = products.stream()
                    .filter(product -> product.getPrice() <= request.getMaxPrice())
                    .collect(Collectors.toList());
        }

        return products.stream()
                .map(this::mapToProductSearchResponse)
                .collect(Collectors.toList());
    }


    private ProductSearchResponse mapToProductSearchResponse(Product product) {
        ProductSearchResponse response = new ProductSearchResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCategoryName(product.getCategory().getName());
        response.setBrandName(product.getBrand().getName());
        response.setSize(product.getSize().getSize());
        response.setColor(product.getColor().getColor());
        response.setImageUrls(productImageRepository.findByProductId(product.getId()).stream()
                .map(ProductImage::getImageUrl).collect(Collectors.toList()));
        return response;
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setStock(product.getStock());
        response.setCategoryName(product.getCategory().getName());
        response.setBrandName(product.getBrand().getName());
        response.setSize(product.getSize().getSize());
        response.setColor(product.getColor().getColor());

        // Get images
        response.setImageUrls(productImageRepository.findByProductId(product.getId())
                .stream().map(ProductImage::getImageUrl).collect(Collectors.toList()));

        // Get tags names from ProductTag
        response.setTagNames(productTagRepository.findByProductId(product.getId())
                .stream()
                .map(productTag -> productTag.getTag().getName()) // Access the name from Tag
                .collect(Collectors.toList()));

        // Get suppliers and inventories
        response.setSuppliers(productSupplierRepository.findByProductId(product.getId())
                .stream()
                .map(supplier -> {
                    ProductSupplierResponse supplierResponse = new ProductSupplierResponse();
                    supplierResponse.setId(supplier.getId());
                    supplierResponse.setSupplierName(supplier.getSupplier().getName()); // Assuming ProductSupplier has a name
                    return supplierResponse;
                })
                .collect(Collectors.toList()));

        response.setInventories(inventoryRepository.findByProductId(product.getId())
                .stream()
                .map(inventory -> {
                    InventoryResponse inventoryResponse = new InventoryResponse();
                    inventoryResponse.setId(inventory.getId());
                    inventoryResponse.setStock(inventory.getStock());
                    return inventoryResponse;
                })
                .collect(Collectors.toList()));

        return response;
    }


}
