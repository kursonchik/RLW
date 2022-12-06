package com.academy.service.interfaces;

import com.academy.dto.AreaDto;
import com.academy.dto.StationDto;
import com.academy.dto.TrackDto;

import java.util.List;

/**
 * @author : Volha Salash
 */
public interface AreaService {

    AreaDto getArea(int id);

    List<AreaDto> getAllAreas();

    void addArea(AreaDto areaDto);

    void editArea(AreaDto areaDto);

    void deleteArea(AreaDto areaDto);

    AreaDto getAreaBetweenStations(StationDto stationFrom, StationDto stationTo);

    List<AreaDto> getAreasByRoute(List<StationDto> route);

    void createArea(StationDto stationDto, int distance, TrackDto trackDto);
}