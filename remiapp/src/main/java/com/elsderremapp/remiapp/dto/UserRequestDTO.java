package com.elsderremapp.remiapp.dto;

import com.elsderremapp.remiapp.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
    private String deviceToken;
}
