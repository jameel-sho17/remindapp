package com.elsderremapp.remiapp.service;

import com.elsderremapp.remiapp.dto.UserRequestDTO;
import com.elsderremapp.remiapp.dto.UserResponseDTO;
import com.elsderremapp.remiapp.exception.ResourceNotFoundException;
import com.elsderremapp.remiapp.model.User;
import com.elsderremapp.remiapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class UserService {
        private final UserRepository userRepository;

        public UserResponseDTO registerUser(UserRequestDTO request) {

            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword()) // encode later
                    .role(request.getRole())
                    .build();

            User savedUser = userRepository.save(user);

            return UserResponseDTO.builder()
                    .id(savedUser.getId())
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .role(savedUser.getRole().name())
                    .build();
        }

        public UserResponseDTO getUserById(Long id) {

            User user = userRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id " + id));

            return UserResponseDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .build();
        }

        public List<UserResponseDTO> getAllUsers() {

            return userRepository.findAll()
                    .stream()
                    .map(user -> UserResponseDTO.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .role(user.getRole().name())
                            .build())
                    .toList();
        }

    }
