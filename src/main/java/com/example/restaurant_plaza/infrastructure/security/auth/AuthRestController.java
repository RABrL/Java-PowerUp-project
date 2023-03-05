package com.example.restaurant_plaza.infrastructure.security.auth;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
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

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register (
          @RequestBody UserRequestDto request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login (
          @RequestBody LoginRequest request
  ) {
    return ResponseEntity.ok(service.login(request));
  }
}
