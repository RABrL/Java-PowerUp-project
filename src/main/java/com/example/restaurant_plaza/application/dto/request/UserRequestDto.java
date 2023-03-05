package com.example.restaurant_plaza.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String email;
    private String password;
    private String roleName;
}
