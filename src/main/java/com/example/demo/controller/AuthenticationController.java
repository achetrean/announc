package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        System.out.println("Yess of cors");
        return ResponseEntity.ok(userService.register(registrationRequest));
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }
}
