package com.academy.mapper;

import com.academy.dto.MappingDto;
import com.academy.model.entity.Mappings;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {StationMapper.class, TrackMapper.class})
public interface MappingMapper {

    MappingDto toDto(Mappings mapping);

    List<MappingDto> toDtoList(List<Mappings> mappings);

    Mappings toEntity(MappingDto mappingDto);
}