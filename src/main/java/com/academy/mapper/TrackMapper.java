package com.academy.mapper;

import com.academy.dto.TrackDto;
import com.academy.model.entity.Tracks;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrackMapper {

    TrackDto toDto(Tracks track);

    List<TrackDto> toDtoList(List<Tracks> tracks);

    Tracks toEntity(TrackDto trackDto);
}