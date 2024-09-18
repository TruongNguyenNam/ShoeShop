package com.example.back_end.service.authention;

import com.example.back_end.dto.authDTO.TokenDTO;
import com.example.back_end.entity.Token;
import com.example.back_end.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
public interface JWTTokenService {
    String generateJWT(String username);

    Authentication parseTokenToUserInformation(HttpServletRequest request);

    Token generateRefreshToken(User user);

    Boolean isRefreshTokenValid(String refreshToken);

    TokenDTO getNewToken(String refreshToken);
}
