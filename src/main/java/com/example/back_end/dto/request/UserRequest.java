package com.example.back_end.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String role;
    private boolean deleted;
}
