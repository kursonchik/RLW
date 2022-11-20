package com.academy.service.interfaces;

import com.academy.dto.MappingDto;
import com.academy.dto.StationDto;
import com.academy.dto.TrackDto;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface MappingService {

    MappingDto getMapping(int id);

    List<MappingDto> getAllMappings();

    List<MappingDto> getMappingsByTrack(TrackDto trackDto);

    void addMapping(MappingDto mappingDto);

    void editMapping(MappingDto mappingDto);

    void deleteMapping(MappingDto mappingDto);

    List<StationDto> getOrderedStationsByTrack(TrackDto trackDto);

    TrackDto getTrack(int id);

    void appendStation(StationDto stationDto, int trackId, String appendLocation);

    int getStationOrder(StationDto stationDto, TrackDto trackDto);

    StationDto getStationByOrder(TrackDto trackDto, int stationOrder);
}
