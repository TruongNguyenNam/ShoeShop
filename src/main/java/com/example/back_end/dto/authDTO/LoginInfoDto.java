package com.example.back_end.dto.authDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginInfoDto {
    private String username;
    private String password;
    private String email;
    private String token;
    private String  refreshToken;

}
