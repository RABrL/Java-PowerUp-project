package com.example.restaurant_plaza.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String email;
    private String password;
    private String roleName;
}
