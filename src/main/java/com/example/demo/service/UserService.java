package com.example.demo.service;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(RegistrationRequest registrationRequest);

    List<UserDto> getAllUsers();
}
