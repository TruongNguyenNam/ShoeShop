package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ReturnResponse {
    private Integer id;
    private Integer orderId;
    private String returnDate;
    private String reason;
    private String status;
    private List<ReturnDetailResponse> returnDetails;




}
