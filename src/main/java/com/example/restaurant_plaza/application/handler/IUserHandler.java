package com.example.restaurant_plaza.application.handler;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.UserResponseDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IUserHandler {
    void saveUser(@NotNull UserRequestDto userRequestDto, Long roleId);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByDni(String dni);

    void updateUser(UserRequestDto userRequestDto);

    void deleteUserByDni(String dni);
}
