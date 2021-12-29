package com.example.demo.dto;

import com.example.demo.entity.Image;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RegistrationRequest {
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email shouldn't be empty")
    private String email;

    @NotEmpty(message = "First name shouldn't be empty")
    private String firstName;

    @NotEmpty(message = "Last name shouldn't be empty")
    private String lastName;

    @NotEmpty(message = "Phone number shouldn't be empty")
    @Pattern(regexp = "^\\+?(?:[0-9] ?){6,14}[0-9]$")
    private String phoneNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;
}

