package com.example.back_end.dto.authDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {

    private String token;
    private String refreshToken;

    public TokenDTO(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
