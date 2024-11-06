package com.example.back_end.service.admin.product;

import com.example.back_end.dto.admin.response.SizeResponse;
import com.example.back_end.entity.Size;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService{

    @Autowired
    private SizeRepository sizeRepository;
    @Override
    public List<SizeResponse> getAll() {
        List<Size> sizes= sizeRepository.findAll();
        if (sizes.isEmpty()){
            throw new AppException("sizes is not found");
        }
        return sizes.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private SizeResponse mapToResponse(Size size){
        SizeResponse response = new SizeResponse();
        response.setId(size.getId());
        response.setSize(size.getSize());
        return response;
    }
}
