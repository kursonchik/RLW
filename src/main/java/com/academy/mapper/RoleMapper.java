package com.academy.mapper;

import com.academy.dto.RoleDto;
import com.academy.model.entity.Roles;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper {

    RoleDto toDto(Roles role);

    Roles toEntity(RoleDto roleDto);
}

