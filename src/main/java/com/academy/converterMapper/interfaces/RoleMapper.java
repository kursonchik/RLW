package com.academy.converterMapper.interfaces;

import com.academy.dto.RoleDto;
import com.academy.model.entity.Roles;

/**
 * @author : Volha Salash
 */
public interface RoleMapper {

    RoleDto toDto(Roles role);

    Roles toEntity(RoleDto roleDto);
}

