package com.example.restaurant_plaza.domain.usecase;

import com.example.restaurant_plaza.domain.api.IUserServicePort;
import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.userPersistencePort = iUserPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUserByDni(String dni) {
        return userPersistencePort.getUserByDni(dni);
    }

    @Override
    public User getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUserByDni(String dni) {
        userPersistencePort.deleteUserByDni(dni);
    }
}
