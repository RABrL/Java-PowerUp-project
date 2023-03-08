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
public class LoginRequestDto {
  @NotBlank(message = "Email is mandatory")
  @Pattern(regexp = "^[a-z0-9+_-]+@[a-z]+\\.[a-z]{2,3}$", message = "Invalid email")
  private String email;
  @NotBlank(message = "Password is mandatory")
  private String password;
}
