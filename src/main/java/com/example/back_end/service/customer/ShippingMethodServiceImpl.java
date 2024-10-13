package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.ShippingMethodResponse;
import com.example.back_end.dto.customer.response.ShippingResponse;
import com.example.back_end.entity.ShippingMethod;
import com.example.back_end.exception.AppException;
import com.example.back_end.repository.ShippingMethodRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShippingMethodServiceImpl implements ShippingMethodService{
    @Autowired
    private ShippingMethodRepository shippingMethodRepository;



    @Override
    public List<ShippingMethodResponse> getAll() {
        List<ShippingMethod> shippingMethods = shippingMethodRepository.findAll();
        if(shippingMethods.isEmpty()){
            throw new AppException("shippingMethod is not empty");
        }

        return shippingMethods.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private ShippingMethodResponse mapToResponse(ShippingMethod shippingMethod){
        ShippingMethodResponse response = new ShippingMethodResponse();
        response.setId(shippingMethod.getId());
        response.setName(shippingMethod.getName());
        response.setCost(shippingMethod.getCost());
        response.setDescription(shippingMethod.getDescription());
        return response;

    }

}
