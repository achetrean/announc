package com.example.demo.controller;

import com.example.demo.dto.ImageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.RoleEnum;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

import static com.example.demo.entity.RoleEnum.Fields.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id")String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id")String id, @RequestBody UserDto userDto) {
        System.out.println(id);
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id")String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PutMapping("/{id}/images")
    @RolesAllowed({ROLE_USER, ROLE_ADMIN,ROLE_SYSADMIN})
    public ResponseEntity<UserDto> uploadProfileImages(@PathVariable(name = "id")String id,
                                                       @RequestBody List<ImageDto> images) {
        return ResponseEntity.ok(userService.uploadProfileImages(id,images));
    }
}
