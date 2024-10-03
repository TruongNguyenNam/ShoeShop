package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.BrandResponse;
import com.example.back_end.entity.Brand;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponse> getALL() {
       List<Brand> brands = brandRepository.findAll();
       if(brands.isEmpty()){
           throw  new AppException("brand is not found");
       }
       return brands.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private BrandResponse mapToResponse(Brand brand){
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        response.setDescription(brand.getDescription());
        return response;
    }

}
