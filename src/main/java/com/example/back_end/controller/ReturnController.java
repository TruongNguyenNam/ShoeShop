package com.example.back_end.controller;

import com.example.back_end.dto.customer.request.ReturnRequest;
import com.example.back_end.dto.customer.response.ReturnResponse;
import com.example.back_end.service.customer.ReturnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/return")
@Validated
public class ReturnController {
    private final ReturnService returnService;
    @Autowired
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @PostMapping("/request")
    public ResponseEntity<ReturnResponse> requestReturn(
            @RequestParam Integer userId,
            @Valid @RequestBody ReturnRequest request) {
        ReturnResponse response = returnService.requestReturn(userId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
