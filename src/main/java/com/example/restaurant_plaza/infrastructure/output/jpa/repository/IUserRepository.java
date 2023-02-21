package com.example.restaurant_plaza.infrastructure.output.jpa.repository;

import com.example.restaurant_plaza.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByDni(String dni);

    void deleteByDni(String dni);
}
