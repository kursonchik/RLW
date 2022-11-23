package com.academy.converterMapper.interfaces;

import com.academy.dto.StationDto;
import com.academy.model.entity.Stations;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface StationMapper {

    StationDto toDto(Stations station);

    List<StationDto> toDtoList(List<Stations> stations);

    Stations toEntity(StationDto trainDto);

    List<Stations> toEntityList(List<StationDto> stationDtoList);

}