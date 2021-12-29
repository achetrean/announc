package com.example.demo.service;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;

public interface UserService {
    UserDto register(RegistrationRequest registrationRequest);
}
