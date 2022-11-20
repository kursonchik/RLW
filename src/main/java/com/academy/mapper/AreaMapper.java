package com.academy.mapper;

import com.academy.dto.AreaDto;
import com.academy.model.entity.Areas;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Volha Salash
 */

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AreaMapper {
    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    /**
     * To dto area dto.
     *
     * @param area the areas entity
     * @return the area dto
     */
    AreaDto toDto(Areas area);

    /**
     * To dto list list.
     *
     * @param areas the area entity list
     * @return the list
     */
    @InheritInverseConfiguration
    List<AreaDto> toDtoList(List<Areas> areas);

    Areas toEntity(AreaDto areaDto);
}