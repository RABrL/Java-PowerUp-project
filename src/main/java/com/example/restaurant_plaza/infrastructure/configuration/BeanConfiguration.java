package com.example.restaurant_plaza.infrastructure.configuration;

import com.example.restaurant_plaza.domain.api.IRoleServicePort;
import com.example.restaurant_plaza.domain.api.IUserServicePort;
import com.example.restaurant_plaza.domain.spi.IRolePersistencePort;
import com.example.restaurant_plaza.domain.spi.IUserPersistencePort;
import com.example.restaurant_plaza.domain.usecase.RoleUseCase;
import com.example.restaurant_plaza.domain.usecase.UserUseCase;
import com.example.restaurant_plaza.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.example.restaurant_plaza.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IRoleRepository;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IUserRepository;
import com.example.restaurant_plaza.infrastructure.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(
                userRepository, userEntityMapper,
                passwordEncoder, jwtService, authenticationManager
        );
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository,roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

}
