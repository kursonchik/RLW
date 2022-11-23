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
public class RoleMapperImpl implements RoleMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RoleDto toDto(Roles role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setName( role.getName() );
        roleDto.setUsers( userEntitySetToUserDtoSet( role.getUsers() ) );

        return roleDto;
    }

    @Override
    public Roles toEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Roles roleEntity = new Roles();

        roleEntity.setId( roleDto.getId() );
        roleEntity.setName( roleDto.getName() );
        roleEntity.setUsers( userDtoSetToUserEntitySet( roleDto.getUsers() ) );

        return roleEntity;
    }

    protected Set<UserDto> userEntitySetToUserDtoSet(Set<Users> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserDto> set1 = new HashSet<UserDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Users userEntity : set ) {
            set1.add( userMapper.toDto( userEntity ) );
        }

        return set1;
    }

    protected Set<Users> userDtoSetToUserEntitySet(Set<UserDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Users> set1 = new HashSet<Users>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserDto userDto : set ) {
            set1.add( userMapper.toEntity( userDto ) );
        }

        return set1;
    }
}
