package com.example.restaurant_plaza.application.handler;

import com.example.restaurant_plaza.application.dto.request.UserRequestDto;
import com.example.restaurant_plaza.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByDni(String dni);

    void updateUser(UserRequestDto userRequestDto);

    void deleteUserByDni(String dni);
}
