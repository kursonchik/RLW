package com.academy.service.interfaces;

import com.academy.dto.StationDto;
import com.academy.dto.TrainDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : Volha Salash
 */
public interface StationService {

    StationDto getStation(int id);

    StationDto getStationByName(String name);

    List<StationDto> getAllStations();

    void addStation(StationDto stationDto);

    void editStation(StationDto stationDto);

// void deleteStation(StationDto stationDto);

    @Transactional
    void deleteStation(StationDto stationDto);

    LinkedList<StationDto> getRoute(String stationFrom, String stationTo);

    int countTrackChanges(LinkedList<StationDto> route);

    List<StationDto> getStationsByTrain(TrainDto trainDto);

    List<StationDto> selectEndpoints(List<StationDto> stationDtoList);

    void setEndpoints(LinkedList<StationDto> route);

    StationDto updateStationDto(StationDto stationDto);
}