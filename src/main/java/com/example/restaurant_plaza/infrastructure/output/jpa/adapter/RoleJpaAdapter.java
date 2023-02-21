package com.example.restaurant_plaza.infrastructure.output.jpa.adapter;

import com.example.restaurant_plaza.domain.model.Role;
import com.example.restaurant_plaza.domain.spi.IRolePersistencePort;
import com.example.restaurant_plaza.infrastructure.exception.NoDataFoundException;
import com.example.restaurant_plaza.infrastructure.exception.RoleNotFoundException;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.RoleEntity;
import com.example.restaurant_plaza.infrastructure.output.jpa.mapper.IRoleEntityMapper;
import com.example.restaurant_plaza.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;
    @Override
    public Role saveRole(Role role) {
        return roleEntityMapper.toRole(roleRepository.save(roleEntityMapper.toEntity(role)));
    }

    @Override
    public List<Role> getAllRoles() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return roleEntityMapper.toRoleList(roleEntityList);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleEntityMapper.toRole(roleRepository.findById(id).orElseThrow(RoleNotFoundException::new));
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(roleEntityMapper.toEntity(role));
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
