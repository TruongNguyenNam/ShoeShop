package com.example.back_end.service.customer;

import com.example.back_end.dto.customer.request.ReturnRequest;
import com.example.back_end.dto.customer.response.ReturnResponse;

public interface ReturnService {
     ReturnResponse requestReturn(Integer userId, ReturnRequest request);
}
