package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class CampaignResponse {
    private Integer id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
