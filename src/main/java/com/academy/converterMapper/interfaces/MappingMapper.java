package com.academy.converterMapper.interfaces;

import com.academy.dto.MappingDto;
import com.academy.model.entity.Mappings;

import java.util.List;

/**
 * @author : Volha Salash
 */


public interface MappingMapper {

    MappingDto toDto(Mappings mapping);

    List<MappingDto> toDtoList(List<Mappings> mappings);

    Mappings toEntity(MappingDto mappingDto);
}
