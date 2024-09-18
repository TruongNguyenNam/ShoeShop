package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReturnResponse {
    private Long id;
    private Long orderId;
    private String reason;
    private String status;
    private LocalDateTime returnDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
