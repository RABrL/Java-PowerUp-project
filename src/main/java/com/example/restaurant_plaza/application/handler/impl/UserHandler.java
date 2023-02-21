package com.example.restaurant_plaza.application.handler.impl;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.UserResponseDto;
import com.example.restaurant_plaza.application.handler.IUserHandler;
import com.example.restaurant_plaza.application.mapper.IRoleDtoMapper;
import com.example.restaurant_plaza.application.mapper.IUserRequestMapper;
import com.example.restaurant_plaza.application.mapper.IUserResponseMapper;
import com.example.restaurant_plaza.domain.api.IRoleServicePort;
import com.example.restaurant_plaza.domain.api.IUserServicePort;
import com.example.restaurant_plaza.domain.model.Role;
import com.example.restaurant_plaza.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    private final IRoleDtoMapper roleDtoMapper;
    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        Long roleId = getRoleByName(userRequestDto.getRoleName()).getId();
        userRequestDto.setRoleName(Long.toString(roleId));
        User user = userRequestMapper.toUser(userRequestDto);
        System.out.println("RoleId user:" + user.getRoleId());
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
    public void updateUser(UserRequestDto userRequestDto) {
        User oldUser = userServicePort.getUserByDni(userRequestDto.getDni());
        Role newRole = getRoleByName(userRequestDto.getRoleName());
        User newUser = userRequestMapper.toUser(userRequestDto);
        newUser.setId(oldUser.getId());
        newUser.setRoleId(newRole.getId());
    }

    @Override
    public void deleteUserByDni(String dni) {
        userServicePort.deleteUserByDni(dni);
    }

    public Role getRoleByName(String roleName) {
        return roleServicePort.getAllRoles().stream()
                .filter(role -> role.getName().equals(roleName))
                .findFirst().orElse(null);
    }
}
