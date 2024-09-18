package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReturnRequest {
        private Integer orderId;
        private String reason;
        private List<ReturnDetailRequest> returnDetails;

}
