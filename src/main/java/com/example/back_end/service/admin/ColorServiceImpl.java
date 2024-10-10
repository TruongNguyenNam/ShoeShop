package com.example.back_end.service.admin;

import com.example.back_end.dto.admin.response.ColorResponse;
import com.example.back_end.entity.Color;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService{
    private final ColorRepository colorRepository;
    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public List<ColorResponse> getAll() {
        List<Color> colors = colorRepository.findAll();
        if(colors.isEmpty()){
            throw new AppException("colors is not found");
        }
        return colors.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private ColorResponse mapToResponse(Color color){
        ColorResponse response = new ColorResponse();
        response.setId(color.getId());
        response.setColor(color.getColor());
        return response;
    }

}
