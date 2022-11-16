package com.academy.mapper;

import com.academy.dto.AreaDto;
import com.academy.model.entity.Areas;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {StationMapper.class, TrackMapper.class})
public interface AreaMapper {

    AreaDto toDto(Areas area);

    List<AreaDto> toDtoList(List<Areas> sections);

    Areas toEntity(AreaDto sectionDto);
}