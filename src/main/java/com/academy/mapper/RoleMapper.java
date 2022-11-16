package com.academy.mapper;

import com.academy.dto.RoleDto;
import com.academy.model.entity.Roles;
import org.mapstruct.Mapper;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {UserMapper.class})
public interface RoleMapper {

    RoleDto toDto(Roles role);

    Roles toEntity(RoleDto roleDto);
}

