package com.example.demo.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @Email(message = "Email should be valid")
    @NotNull(message = "Email shouldn't be empty")
    private String email;
    @NotNull(message = "Password shouldn't be empty")
    private String password;
}
