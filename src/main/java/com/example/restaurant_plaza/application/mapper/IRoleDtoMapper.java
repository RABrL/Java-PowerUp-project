package com.example.restaurant_plaza.application.mapper;

import com.example.restaurant_plaza.application.dto.RoleDto;
import com.example.restaurant_plaza.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleDtoMapper {

    RoleDto toDto(Role role);
}
