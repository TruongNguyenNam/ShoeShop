package com.example.back_end.dto.admin.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String message;
    private String role;
    private boolean deleted;
}