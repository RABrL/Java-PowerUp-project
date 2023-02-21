package com.example.restaurant_plaza.domain.spi;

import com.example.restaurant_plaza.domain.model.Role;
import com.example.restaurant_plaza.domain.model.User;

import java.util.List;

public interface IRolePersistencePort {
    Role saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    void updateRole(Role role);

    void deleteRoleById(Long id);
}
