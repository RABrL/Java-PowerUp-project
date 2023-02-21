package com.example.restaurant_plaza.infrastructure.output.jpa.mapper;

import com.example.restaurant_plaza.domain.model.User;
import com.example.restaurant_plaza.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(source = "roleId", target = "role.id")
    UserEntity toEntity(User user);

    @Mapping(source = "role.id", target = "roleId")
    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);
}
