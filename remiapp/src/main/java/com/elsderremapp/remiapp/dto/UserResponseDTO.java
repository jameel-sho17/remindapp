package com.elsderremapp.remiapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private String deviceToken;
}
