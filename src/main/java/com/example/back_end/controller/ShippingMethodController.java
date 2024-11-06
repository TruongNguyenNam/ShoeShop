package com.example.back_end.controller;

import com.example.back_end.dto.customer.response.ShippingMethodResponse;
import com.example.back_end.service.customer.ShippingMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/shippingMethod")
@Validated
public class ShippingMethodController {

    @Autowired
    private ShippingMethodService shippingMethodService;

    @GetMapping
    public ResponseEntity<List<ShippingMethodResponse>> getAll() {
        List<ShippingMethodResponse> shippingMethodResponses = shippingMethodService.getAll();
        return ResponseEntity.ok(shippingMethodResponses);
    }

}
