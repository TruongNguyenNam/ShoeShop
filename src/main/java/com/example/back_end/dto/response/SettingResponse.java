package com.example.back_end.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SettingResponse {
    private Long id;
    private String key;
    private String value;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
