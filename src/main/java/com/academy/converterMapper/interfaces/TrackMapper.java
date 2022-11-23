package com.academy.converterMapper.interfaces;

import com.academy.dto.TrackDto;
import com.academy.model.entity.Tracks;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TrackMapper {

    TrackDto toDto(Tracks track);

    List<TrackDto> toDtoList(List<Tracks> tracks);

    Tracks toEntity(TrackDto trackDto);

}