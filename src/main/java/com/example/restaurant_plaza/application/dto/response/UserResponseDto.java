package com.example.restaurant_plaza.application.dto.response;

import com.example.restaurant_plaza.application.dto.RoleDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String nameUser;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String email;
    private RoleDto role;
}
