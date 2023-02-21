package com.example.restaurant_plaza.domain.usecase;

import com.example.restaurant_plaza.domain.api.IRoleServicePort;
import com.example.restaurant_plaza.domain.model.Role;
import com.example.restaurant_plaza.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(Role role) {
        rolePersistencePort.saveRole(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return rolePersistencePort.getAllRoles();
    }

    @Override
    public Role getRoleById(Long id) {
        return rolePersistencePort.getRoleById(id);
    }

    @Override
    public void updateRole(Role role) {
        rolePersistencePort.updateRole(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        rolePersistencePort.deleteRoleById(id);
    }
}

