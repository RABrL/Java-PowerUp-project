package com.example.restaurant_plaza.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "LastName is mandatory")
    private String lastName;
    @NotBlank(message = "Dni is mandatory")
    @Pattern(regexp = "^\\d{4,10}$", message = "The dni must have between 4 and 10 numbers")
    private String dni;
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^(\\+\\d{12}|\\d{10})$", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-z0-9+_-]+@[a-z]+\\.[a-z]{2,3}$", message = "Invalid email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String roleName;
}
