package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.AreaMapper;
import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.converterMapper.interfaces.TrackMapper;
import com.academy.dto.AreaDto;
import com.academy.model.entity.Areas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AreaMapperImpl implements AreaMapper {

    private final StationMapper stationMapper;
    private final TrackMapper trackMapper;

    @Autowired
    public AreaMapperImpl(StationMapper stationMapper, TrackMapper trackMapper) {

        this.stationMapper = stationMapper;
        this.trackMapper = trackMapper;
    }

    @Override
    public AreaDto toDto(Areas area) {
        if (area == null) {
            return null;
        }
        AreaDto areaDto = new AreaDto();

        areaDto.setId(area.getId());
        areaDto.setStationFrom(stationMapper.toDto(area.getStationFrom()));
        areaDto.setStationTo(stationMapper.toDto(area.getStationTo()));
        areaDto.setDistance(area.getDistance());
        areaDto.setTrack(trackMapper.toDto(area.getTrack()));
        areaDto.setDirection(area.isDirection());

        return areaDto;
    }

    @Override
    public List<AreaDto> toDtoList(List<Areas> areas) {
        if (areas == null) {
            return null;
        }

        List<AreaDto> list = new ArrayList<AreaDto>(areas.size());
        for (Areas areas1 : areas) {
            list.add(toDto(areas1));
        }

        return list;
    }

    @Override
    public Areas toEntity(AreaDto areaDto) {
        if (areaDto == null) {
            return null;
        }

        Areas areas = new Areas();

        areas.setId(areaDto.getId());
        areas.setStationFrom(stationMapper.toEntity(areaDto.getStationFrom()));
        areas.setStationTo(stationMapper.toEntity(areaDto.getStationTo()));
        areas.setDistance(areaDto.getDistance());
        areas.setTrack(trackMapper.toEntity(areaDto.getTrack()));
        areas.setDirection(areaDto.isDirection());

        return areas;
    }
}
