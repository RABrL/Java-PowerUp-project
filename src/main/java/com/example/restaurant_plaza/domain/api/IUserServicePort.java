package com.example.restaurant_plaza.domain.api;

import com.example.restaurant_plaza.domain.model.User;

import java.util.List;

public interface IUserServicePort {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserByDni(String dni);

    void updateUser(User user);

    void deleteUserByDni(String dni);
}
