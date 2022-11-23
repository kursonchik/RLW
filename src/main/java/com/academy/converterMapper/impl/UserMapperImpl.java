package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.UserMapper;
import com.academy.dto.UserDto;
import com.academy.model.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(Users users) {
        UserDto userDto = new UserDto();
        if (users != null) {
            userDto.setUsername(users.getUsername());
            userDto.setPassword(users.getPassword());
            userDto.setPasswordConfirm(users.getPasswordConfirm());
     //       userDto.setRoleDto(users.getRoles());
        }
        return userDto;
    }

    @Override
    public Users toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        Users users = new Users();

        users.setPassword(userDto.getPassword());
        users.setPasswordConfirm(userDto.getPasswordConfirm());

        return users;
    }
}
