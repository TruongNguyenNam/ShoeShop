package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.response.ShippingMethodResponse;

import java.util.List;

public interface ShippingMethodService {

    List<ShippingMethodResponse> getAll();

}
