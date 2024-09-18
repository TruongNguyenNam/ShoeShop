package com.example.back_end.controller.auth;



import com.example.back_end.dto.authDTO.LoginInfoDto;
import com.example.back_end.dto.authDTO.RegisterForm;
import com.example.back_end.dto.authDTO.TokenDTO;
import com.example.back_end.dto.authDTO.loginForm;
import com.example.back_end.dto.request.UserRequest;
import com.example.back_end.dto.response.UserResponse;
import com.example.back_end.entity.User;
import com.example.back_end.repository.UserRepository;
import com.example.back_end.service.authention.IAuthService;
import com.example.back_end.service.authention.IJWTTokenService;
import com.example.back_end.validation.RefreshTokenValid;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/auth")
@Validated
public class AuthController {


    @Autowired
    private IAuthService authService;

    @Autowired
    private IJWTTokenService ijwtTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/login")
    public LoginInfoDto login(@RequestBody @Valid loginForm loginForm){
                    Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getUsername(),
                            loginForm.getPassword())
            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    return authService.login(loginForm.getUsername());
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<TokenDTO> refreshToken(@RefreshTokenValid String refreshToken) {
        try {
            TokenDTO newToken = ijwtTokenService.getNewToken(refreshToken);
            return ResponseEntity.ok(newToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterForm registerForm) {
        UserResponse userResponse = authService.register(registerForm);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }


//    @PostMapping("/addUser")
//    public UserResponse addUser(@RequestBody UserRequest userRequest) {
//        String encodedPassword = encoder.encode(userRequest.getPassword());
//        User newUser = new User();
//        newUser.setUsername(userRequest.getUsername());
//        newUser.setPassword(encodedPassword);
//        newUser.setEmail(userRequest.getEmail());
//        newUser.setRole(User.Role.ADMIN);
//        newUser.setDeleted(userRequest.isDeleted());
//
//        User savedUser = userRepository.save(newUser);
//        return modelMapper.map(savedUser, UserResponse.class);
//    }
//    @Autowired
//    private ModelMapper modelMapper;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder encoder;




}
