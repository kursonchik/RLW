package com.academy.mapper;

import com.academy.dto.StationDto;
import com.academy.model.entity.Stations;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {ScheduleMapper.class, MappingMapper.class})
public interface StationMapper {

    StationDto toDto(Stations station);

    List<StationDto> toDtoList(List<Stations> stations);

    Stations toEntity(StationDto trainDto);

    List<Stations> toEntityList(List<StationDto> stationDtoList);

}