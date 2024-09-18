package com.example.back_end.dto.authDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class loginForm {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}