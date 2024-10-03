package com.example.back_end.service.authention;
import com.example.back_end.dto.authDTO.LoginInfoDto;
import com.example.back_end.dto.authDTO.RegisterForm;
import com.example.back_end.dto.admin.response.UserResponse;
import com.example.back_end.entity.Token;
import com.example.back_end.entity.User;
import com.example.back_end.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IAuthService implements AuthService {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTTokenService ijwtTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public LoginInfoDto login(String username) {
        User entity = service.getAccountByUsername(username);

        LoginInfoDto dto = modelMapper.map(entity, LoginInfoDto.class);

        dto.setToken(ijwtTokenService.generateJWT(entity.getUsername()));

        Token token = ijwtTokenService.generateRefreshToken(entity);
        dto.setRefreshToken(token.getKey());

        return dto;
    }

    @Override
    public UserResponse register(RegisterForm registerForm) {
        if (userRepository.existsByUsername(registerForm.getUsername())) {
            throw new IllegalArgumentException("Username đã tồn tại.");
        }

        if (userRepository.existsByEmail(registerForm.getEmail())) {
            throw new IllegalArgumentException("Email đã được sử dụng.");
        }

        User user = modelMapper.map(registerForm, User.class);

        user.setPassword(passwordEncoder.encode(registerForm.getPassword()));

        user.setRole(User.Role.ADMIN);

        User savedUser = userRepository.save(user);

        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        userResponse.setMessage("Đăng ký thành công.");
        userResponse.setRole("USER");
        userResponse.setDeleted(false);

        return userResponse;

    }


}
