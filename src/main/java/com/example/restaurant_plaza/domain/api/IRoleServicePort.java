package com.example.restaurant_plaza.domain.api;

import com.example.restaurant_plaza.domain.model.Role;

import java.util.List;

public interface IRoleServicePort {
    void saveRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    void updateRole(Role role);

    void deleteRoleById(Long id);
}
