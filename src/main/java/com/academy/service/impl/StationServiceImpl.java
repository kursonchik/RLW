package com.academy.service.impl;

import com.academy.dto.AreaDto;
import com.academy.dto.StationDto;
import com.academy.dto.TrainDto;
import com.academy.mapper.StationMapper;
import com.academy.mapper.TrainMapper;
import com.academy.model.repository.interfaces.StationRepository;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.PathFinderService;
import com.academy.service.interfaces.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final PathFinderService pathFinderService;
    private final AreaService areaService;
    private final StationMapper stationMapper;
    @Autowired
    private final TrainMapper trainMapper;

    @Override
    public StationDto getStation(int id) {
        return stationMapper.toDto(stationRepository.getStation(id));
    }

    @Override
    public StationDto getStationByName(String name) {
        return stationMapper.toDto(stationRepository.getStationByName(name));
    }

    @Override
    public List<StationDto> getAllStations() {
        return stationMapper.toDtoList(stationRepository.getAllStations());
    }

    @Override
    @Transactional
    public void addStation(StationDto stationDto) {
        stationRepository.addStation(stationMapper.toEntity(stationDto));
        log.info("Created new station " + stationDto.getName());
    }

    @Override
    @Transactional
    public void editStation(StationDto stationDto) {
        stationRepository.editStation(stationMapper.toEntity(stationDto));
        log.info("Edited station " + stationDto.getName());
    }
/*
    @Override
    @Transactional
    public void deleteStation(StationDto stationDto) {
        if (stationDto.getId() <= 18) {
            throw new IllegalOperationException("Attempted to delete a core station.");
        }
        stationRepository.deleteStation(stationMapper.toEntity(stationDto));
        log.info("Deleted station " + stationDto.getName());
        messagingService.sendMessage();
    }

 */

    @Override
    public LinkedList<StationDto> getRoute(String stationFrom, String stationTo) {
        pathFinderService.initialize(getStationByName(stationFrom));
        return pathFinderService.createRoute(getStationByName(stationTo));
    }

    @Override
    public int countTrackChanges(LinkedList<StationDto> route) {
        setEndpoints(route);
        int counter = 0;
        if (route.size() > 2) {
            for (int i = 0; i < route.size() - 2; i++) {
                AreaDto area1 = areaService.getAreaBetweenStations(route.get(i), route.get(i + 1));
                AreaDto area2 = areaService.getAreaBetweenStations(route.get(i + 1), route.get(i + 2));
                if (!area1.getTrack().equals(area2.getTrack())) {
                    route.get(i + 1).setBreakpoint(true);
                    route.get(i + 1).setEndpoint(false);
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public List<StationDto> getStationsByTrain(TrainDto trainDto) {
        return stationMapper.toDtoList(stationRepository.getStationsByTrain(trainMapper.toEntity(trainDto)));
    }

    @Override
    public List<StationDto> selectEndpoints(List<StationDto> stationDtoList) {
        List<StationDto> endpoints = new ArrayList<>();
        endpoints.add(stationDtoList.get(0));
        endpoints.add(stationDtoList.get(stationDtoList.size() - 1));
        return endpoints;
    }

    @Override
    public void setEndpoints(LinkedList<StationDto> route) {
        route.get(0).setEndpoint(true);
        route.get(route.size() - 1).setEndpoint(true);
    }

    @Override
    public StationDto updateStationDto(StationDto stationDto) {
        return getStationByName(stationDto.getName());
    }
}