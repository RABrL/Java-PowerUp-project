package com.example.restaurant_plaza.infrastructure.security.auth;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.handler.impl.UserHandler;
import com.example.restaurant_plaza.application.mapper.IUserRequestMapper;
import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.RoleEntity;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.UserEntity;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IRoleRepository;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IUserRepository;

import com.example.restaurant_plaza.infrastructure.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final IUserRepository userRepository;
  private final IRoleRepository roleRepository;
  private final IUserRequestMapper userRequestMapper;
  private final IUserEntityMapper userEntityMapper;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  public AuthenticationResponse register(UserRequestDto request) {
    request.setRoleName(Long.toString(4));
    UserEntity userEntity = userEntityMapper.toEntity(userRequestMapper.toUser(request));
    RoleEntity role = userEntity.getRole();
    var user = UserEntity.builder()
            .name(request.getName())
            .lastName(request.getLastName())
            .dni(request.getDni())
            .phoneNumber(request.getPhoneNumber())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(role)
            .build();
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }

  public AuthenticationResponse login(LoginRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
  }
}
