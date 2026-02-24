package com.elsderremapp.remiapp.controller;


import com.elsderremapp.remiapp.dto.UserRequestDTO;
import com.elsderremapp.remiapp.dto.UserResponseDTO;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody UserRequestDTO request) {
        return userService.registerUser(request);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/allusers")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

}
