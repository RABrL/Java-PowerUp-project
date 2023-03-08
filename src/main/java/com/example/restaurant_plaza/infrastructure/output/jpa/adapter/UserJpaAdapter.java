package com.example.restaurant_plaza.infrastructure.output.jpa.adapter;

import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.domain.spi.IUserPersistencePort;
import com.example.restaurant_plaza.infrastructure.exception.NoDataFoundException;
import com.example.restaurant_plaza.infrastructure.exception.UserAlreadyExistException;
import com.example.restaurant_plaza.infrastructure.exception.UserNotFoundException;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.UserEntity;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        if(userRepository.findByDni(user.getDni()).isPresent() ||
                userRepository.findByEmail(user.getEmail()).isPresent()
        ) {
            throw new UserAlreadyExistException();
        }
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity);
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
        return userEntityMapper.toUser(userRepository.findByDni(dni).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUserByDni(String dni) {
        userRepository.deleteByDni(dni);
    }
}
