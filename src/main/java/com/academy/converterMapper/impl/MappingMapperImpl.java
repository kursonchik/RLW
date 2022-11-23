package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.MappingMapper;
import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.converterMapper.interfaces.TrackMapper;
import com.academy.dto.MappingDto;
import com.academy.model.entity.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MappingMapperImpl implements MappingMapper {

    private final StationMapper stationMapper;
    private final TrackMapper trackMapper;

    @Autowired
    public MappingMapperImpl(StationMapper stationMapper, TrackMapper trackMapper) {

        this.stationMapper = stationMapper;
        this.trackMapper = trackMapper;
    }

    @Override
    public MappingDto toDto(Mappings mapping) {
        if (mapping == null) {
            return null;
        }

        MappingDto mappingDto = new MappingDto();

        mappingDto.setId(mapping.getId());
        mappingDto.setStation(stationMapper.toDto(mapping.getStation()));
        mappingDto.setTrack(trackMapper.toDto(mapping.getTrack()));
        mappingDto.setStationOrder(mapping.getStationOrder());

        return mappingDto;
    }

    @Override
    public List<MappingDto> toDtoList(List<Mappings> mappings) {
        if (mappings == null) {
            return null;
        }

        List<MappingDto> list = new ArrayList<MappingDto>(mappings.size());
        for (Mappings mappings1 : mappings) {
            list.add(toDto(mappings1));
        }

        return list;
    }

    @Override
    public Mappings toEntity(MappingDto mappingDto) {
        if (mappingDto == null) {
            return null;
        }

        Mappings mappings = new Mappings();

        mappings.setId(mappingDto.getId());
        mappings.setStation(stationMapper.toEntity(mappingDto.getStation()));
        mappings.setTrack(trackMapper.toEntity(mappingDto.getTrack()));
        mappings.setStationOrder(mappingDto.getStationOrder());

        return mappings;
    }
}
