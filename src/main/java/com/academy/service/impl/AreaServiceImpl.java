package com.academy.service.impl;

import com.academy.dto.AreaDto;
import com.academy.dto.StationDto;
import com.academy.dto.TrackDto;
import com.academy.mapper.AreaMapper;
import com.academy.mapper.StationMapper;
import com.academy.mapper.TrackMapper;
import com.academy.model.entity.Areas;
import com.academy.model.repository.interfaces.AreaRepository;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final MappingService mappingService;
    private final AreaMapper areaMapper;
    private final StationMapper stationMapper;
    private final TrackMapper trackMapper;

    @Override
    public AreaDto getArea(int id) {
        return areaMapper.toDto(areaRepository.getArea(id));
    }

    @Override
    public List<AreaDto> getAllAreas() {
        return areaMapper.toDtoList(areaRepository.getAllAreas());
    }

    @Override
    @Transactional
    public void addArea(AreaDto areaDto) {
        areaRepository.addArea(areaMapper.toEntity(areaDto));
        log.info("Created new section between " + areaDto.getStationFrom() + " and " + areaDto.getStationTo());
    }

    @Override
    @Transactional
    public void editArea(AreaDto areaDto) {
        areaRepository.editArea(areaMapper.toEntity(areaDto));
        log.info("Edited section between " + areaDto.getStationFrom() + " and " + areaDto.getStationTo());
    }

    @Override
    @Transactional
    public void deleteArea(AreaDto areaDto) {
        areaRepository.deleteArea(areaMapper.toEntity(areaDto));
        log.info("Deleted section between " + areaDto.getStationFrom() + " and " + areaDto.getStationTo());
    }

    @Override
    public AreaDto getAreaBetweenStations(StationDto stationFrom, StationDto stationTo) {
        return areaMapper.toDto(areaRepository.getAreaBetweenStations(stationMapper.toEntity(stationFrom),
                stationMapper.toEntity(stationTo)));
    }

    @Override
    public List<AreaDto> getAreasByRoute(List<StationDto> route) {
        return areaMapper.toDtoList(areaRepository.getAreasByRoute(stationMapper.toEntityList(route)));
    }

    @Override
    @Transactional
    public void createArea(StationDto stationDto, int length, TrackDto trackDto) {
        StationDto nearestStation;
        int stationOrder = mappingService.getStationOrder(stationDto, trackDto);
        if (stationOrder == 1) {
            nearestStation = mappingService.getStationByOrder(trackDto, 2);
            areaRepository.addArea(new Areas(stationMapper.toEntity(stationDto),
                    stationMapper.toEntity(nearestStation), length, trackMapper.toEntity(trackDto), true));
            areaRepository.addArea(new Areas(stationMapper.toEntity(nearestStation),
                    stationMapper.toEntity(stationDto), length, trackMapper.toEntity(trackDto), false));
        } else {
            nearestStation = mappingService.getStationByOrder(trackDto, stationOrder - 1);
            areaRepository.addArea(new Areas(stationMapper.toEntity(stationDto),
                    stationMapper.toEntity(nearestStation), length, trackMapper.toEntity(trackDto), false));
            areaRepository.addArea(new Areas(stationMapper.toEntity(nearestStation),
                    stationMapper.toEntity(stationDto), length, trackMapper.toEntity(trackDto), true));
        }
    }
}