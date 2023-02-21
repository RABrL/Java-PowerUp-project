package com.example.restaurant_plaza.infrastructure.output.jpa.repository;

import com.example.restaurant_plaza.infrastructure.output.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}
