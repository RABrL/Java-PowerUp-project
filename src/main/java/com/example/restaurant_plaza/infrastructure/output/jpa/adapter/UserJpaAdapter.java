package com.example.restaurant_plaza.infrastructure.output.jpa.adapter;

import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.domain.spi.IUserPersistencePort;
import com.example.restaurant_plaza.infrastructure.exception.EmailOrPasswordIncorrectException;
import com.example.restaurant_plaza.infrastructure.exception.NoDataFoundException;
import com.example.restaurant_plaza.infrastructure.exception.UserAlreadyExistException;
import com.example.restaurant_plaza.infrastructure.exception.UserNotFoundException;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.UserEntity;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IUserRepository;
import com.example.restaurant_plaza.infrastructure.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String saveUser(User user) {
        boolean existUserByDni = userRepository.findByDni(user.getDni()).isPresent();
        boolean existUserByEmail = userRepository.findByEmail(user.getEmail()).isPresent();
        if( existUserByDni || existUserByEmail ) {
            throw new UserAlreadyExistException();
        }

        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);

        return jwtService.generateToken(userEntity);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUserByDni(String dni) {
        return userEntityMapper
                .toUser(userRepository.findByDni(dni).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper
                .toUser(userRepository.findByEmail(email).orElseThrow(EmailOrPasswordIncorrectException::new));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUserByDni(String dni) {
        userRepository.deleteByDni(dni);
    }

    @Override
    public String login(String email, String password) {
        User user = getUserByEmail(email);
        boolean matched = BCrypt.checkpw(password, user.getPassword());

        if (!matched) {
            throw new EmailOrPasswordIncorrectException();
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return jwtService.generateToken(userEntityMapper.toEntity(user));
    }
}
