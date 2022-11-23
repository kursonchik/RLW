package com.academy.converterMapper.interfaces;

import com.academy.dto.AreaDto;
import com.academy.model.entity.Areas;

import java.util.List;

/**
 * @author : Volha Salash
 */


public interface AreaMapper {

    AreaDto toDto(Areas area);

    List<AreaDto> toDtoList(List<Areas> areas);

    Areas toEntity(AreaDto areaDto);
}