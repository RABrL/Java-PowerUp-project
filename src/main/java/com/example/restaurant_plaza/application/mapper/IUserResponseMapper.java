package com.example.restaurant_plaza.application.mapper;

import com.example.restaurant_plaza.application.dto.response.UserResponseDto;
import com.example.restaurant_plaza.domain.model.Role;
import com.example.restaurant_plaza.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {IRoleDtoMapper.class})
public interface IUserResponseMapper {

    IRoleDtoMapper INSTANCE = Mappers.getMapper(IRoleDtoMapper.class);

    @Mapping(source = "user.name", target = "name")
    UserResponseDto toResponse(User user, Role role);

    default List<UserResponseDto> toResponseList(List<User> userList, List<Role> roleList) {
        if (userList.isEmpty()) {
            return new ArrayList<>();
        }
        return userList.stream()
                .map(user -> {
                    UserResponseDto userResponse = new UserResponseDto();
                    userResponse.setName(user.getName());
                    userResponse.setLastName(user.getLastName());
                    userResponse.setDni(user.getDni());
                    userResponse.setPhoneNumber(user.getPhoneNumber());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setRole(INSTANCE.toDto(roleList.stream()
                            .filter(role -> role.getId().equals(user.getRoleId()))
                            .findFirst().orElse(null)));
                    return userResponse;
                }).toList();
    }
}
