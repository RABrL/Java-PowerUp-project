package com.example.restaurant_plaza.infrastructure.security.auth;

import com.example.restaurant_plaza.application.dto.request.LoginRequestDto;
import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.AuthResponseDto;
import com.example.restaurant_plaza.application.mapper.IUserRequestMapper;
import com.example.restaurant_plaza.domain.api.IUserServicePort;
import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IUserEntityMapper;

import com.example.restaurant_plaza.infrastructure.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final IUserRequestMapper userRequestMapper;
  private final IUserEntityMapper userEntityMapper;
  private final IUserServicePort userServicePort;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthResponseDto register(@NotNull UserRequestDto request) {
    request.setRoleId(4L);
    User user = userRequestMapper.toUser(request);
    userServicePort.saveUser(user);
    String jwtToken = jwtService.generateToken(userEntityMapper.toEntity(user));
    return AuthResponseDto.builder()
            .token(jwtToken)
            .build();
  }

  public AuthResponseDto login(@NotNull LoginRequestDto request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword())
    );
    var user = userEntityMapper.toEntity(userServicePort.getUserByEmail(request.getEmail()));
    var jwtToken = jwtService.generateToken(user);
    return AuthResponseDto.builder()
            .token(jwtToken)
            .build();
  }
}
