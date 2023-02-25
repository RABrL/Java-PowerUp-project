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

import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if(userRepository.findByDni(user.getDni()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(userEntityMapper.toEntity(user));
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
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUserByDni(String dni) {
        userRepository.deleteByDni(dni);
    }
}
