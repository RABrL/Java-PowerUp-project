package com.example.restaurant_plaza.domain.spi;

import com.example.restaurant_plaza.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {

    String saveUser(User user);

    List<User> getAllUsers();

    User getUserByDni(String dni);

    User getUserByEmail(String email);

    void updateUser(User user);

    void deleteUserByDni(String dni);

    String login(String email, String password);
}
