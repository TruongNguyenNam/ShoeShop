package com.example.back_end.dto.customer.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RewardResponse {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime earnedAt;
    private LocalDateTime expiresAt;
    private Boolean isRedeemed;
    private Integer points;
    private Integer userId;


}
