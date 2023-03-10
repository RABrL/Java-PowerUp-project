package com.example.restaurant_plaza.infrastructure.input.rest;

import com.example.restaurant_plaza.application.dto.request.LoginRequestDto;
import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.AuthResponseDto;
import com.example.restaurant_plaza.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

  private final IUserHandler userHandler;

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDto> register (@Valid @RequestBody UserRequestDto request) {
    return ResponseEntity.ok(userHandler.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDto> login (@Valid @RequestBody LoginRequestDto request ) {
    return ResponseEntity.ok(userHandler.login(request));
  }
}
