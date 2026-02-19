package com.elsderremapp.remiapp.service;

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

        public User registerUser(User user) {
            return userRepository.save(user);
        }

        public User getUserById(Long id) {
            return userRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found with id " + id));
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
