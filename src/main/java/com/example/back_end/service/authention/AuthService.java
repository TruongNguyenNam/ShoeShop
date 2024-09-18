package com.example.back_end.service.authention;


import com.example.back_end.dto.authDTO.LoginInfoDto;
import com.example.back_end.dto.authDTO.RegisterForm;
import com.example.back_end.dto.response.UserResponse;

public interface AuthService {
    LoginInfoDto login(String username);

    UserResponse register(RegisterForm registerForm);

}
