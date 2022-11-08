package com.academy.mapper;

import com.academy.dto.RoleDto;
import com.academy.model.entity.Role;
import com.academy.model.entity.Users;
import org.mapstruct.Mapper;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {Users.class})
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
