package com.example.back_end.dto.admin.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReturnRequest {
    private Long orderId;
    private String reason;
    private String status;
}
