package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@Valid RegistrationRequest registrationRequest) {
        System.out.println("Yess of cors");
        return ResponseEntity.ok(userService.register(registrationRequest));
    }
}
