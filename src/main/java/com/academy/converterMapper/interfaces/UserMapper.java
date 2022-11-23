package com.academy.converterMapper.interfaces;

import com.academy.dto.UserDto;
import com.academy.model.entity.Users;

/**
 * @author : Volha Salash
 */

public interface UserMapper {

    UserDto toDto(Users user);

    Users toEntity(UserDto userDto);
}