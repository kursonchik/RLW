package com.academy.converterMapper.impl;
import com.academy.converterMapper.interfaces.RoleMapper;
import com.academy.converterMapper.interfaces.UserMapper;
import com.academy.dto.RoleDto;
import com.academy.dto.UserDto;
import com.academy.model.entity.Roles;
import com.academy.model.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDto toDto(Users user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPasswordConfirm(user.getPasswordConfirm());
        userDto.setRoles(roleEntitySetToRoleDtoSet(user.getRoles()));

        return userDto;
    }

    @Override
    public Users toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        Users userEntity = new Users();

        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setPasswordConfirm(userDto.getPasswordConfirm());
        userEntity.setRoles(roleDtoSetToRoleEntitySet(userDto.getRoles()));

        return userEntity;
    }

    protected Set<RoleDto> roleEntitySetToRoleDtoSet(Set<Roles> set) {
        if (set == null) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Roles roleEntity : set) {
            set1.add(roleMapper.toDto(roleEntity));
        }

        return set1;
    }

    protected Set<Roles> roleDtoSetToRoleEntitySet(Set<RoleDto> set) {
        if (set == null) {
            return null;
        }

        Set<Roles> set1 = new HashSet<Roles>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (RoleDto roleDto : set) {
            set1.add(roleMapper.toEntity(roleDto));
        }

        return set1;
    }
}
