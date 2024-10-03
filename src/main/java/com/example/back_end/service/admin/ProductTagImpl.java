package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.ProductTagResponse;
import com.example.back_end.entity.ProductTag;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.ProductTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTagImpl implements ProductTagService{

    @Autowired
    private ProductTagRepository productTagRepository;

    @Override
    public List<ProductTagResponse> getAll() {
        List<ProductTag> productTags = productTagRepository.findAll();
        if(productTags.isEmpty()){
            throw  new AppException("productTag is not found");
        }
        return  productTags.stream().map(this::mapToResponse).collect(Collectors.toList());

    }

    private ProductTagResponse mapToResponse(ProductTag productTag){
            ProductTagResponse response = new ProductTagResponse();
            response.setId(productTag.getId());
            response.setTagId(productTag.getTag().getId());
            response.setProductId(productTag.getProduct().getId());
            return response;
    }

}
