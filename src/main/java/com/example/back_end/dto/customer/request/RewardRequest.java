package com.example.back_end.dto.customer.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class RewardRequest {
    private LocalDateTime earnedAt;
    private LocalDateTime expiresAt;
    private Boolean isRedeemed;
    private Integer points;
    private Integer userId;


}
