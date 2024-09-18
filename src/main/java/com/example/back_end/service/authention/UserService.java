package com.example.back_end.service.authention;


import com.example.back_end.dto.request.UserRequest;
import com.example.back_end.dto.response.UserResponse;
import com.example.back_end.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Integer userId);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Integer userId, UserRequest userRequestDTO);

    void softDeleteUser(Integer userId);

    User getAccountByUsername(String username);
}
