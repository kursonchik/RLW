package com.academy.mapper;

import com.academy.dto.UserDto;
import com.academy.model.entity.Users;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toDto(Users user);

    Users toEntity(UserDto userDto);
}