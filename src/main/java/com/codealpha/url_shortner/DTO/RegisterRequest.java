package com.codealpha.url_shortner.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String role;
    private String password;
}
