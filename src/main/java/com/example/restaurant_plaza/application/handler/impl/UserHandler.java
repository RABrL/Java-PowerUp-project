package com.example.restaurant_plaza.application.handler.impl;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.UserResponseDto;
import com.example.restaurant_plaza.application.handler.IUserHandler;
import com.example.restaurant_plaza.application.mapper.IUserRequestMapper;
import com.example.restaurant_plaza.application.mapper.IUserResponseMapper;
import com.example.restaurant_plaza.domain.api.IRoleServicePort;
import com.example.restaurant_plaza.domain.api.IUserServicePort;
import com.example.restaurant_plaza.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveUser(@NotNull UserRequestDto userRequestDto, Long roleId) {
        userRequestDto.setRoleId(roleId);
        User user = userRequestMapper.toUser(userRequestDto);
        userServicePort.saveUser(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers(), roleServicePort.getAllRoles());
    }

    @Override
    public UserResponseDto getUserByDni(String dni) {
        User user = userServicePort.getUserByDni(dni);
        return userResponseMapper.toResponse(user, roleServicePort.getRoleById(user.getRoleId()));
    }

    @Override
    public void updateUser(@NotNull UserRequestDto userRequestDto) {
        User oldUser = userServicePort.getUserByDni(userRequestDto.getDni());
        User newUser = userRequestMapper.toUser(userRequestDto);
        newUser.setId(oldUser.getId());
        userServicePort.updateUser(newUser);
    }

    @Override
    public void deleteUserByDni(String dni) {
        userServicePort.deleteUserByDni(dni);
    }
}
