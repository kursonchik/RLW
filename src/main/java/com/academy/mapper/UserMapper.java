package com.academy.mapper;
/*
import com.academy.dto.UserDto;
import com.academy.model.entity.Users;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author : Volha Salash
 */
/*
@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * To dto user dto.
     *
     * @param user the user entity
     * @return the user dto
     */
/*
    UserDto toDto(Users user);
/*
    /**
     * To entity user entity.
     *
     * @param userDto the user dto
     * @return the user entity
     */
/*
    @Mapping(target = "roles", ignore = true)
    Users toEntity(UserDto userDto);
}

 */