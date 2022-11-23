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

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private TrackMapper trackMapper;

    @Override
    public MappingDto toDto(Mappings mapping) {
        if ( mapping == null ) {
            return null;
        }

        MappingDto mappingDto = new MappingDto();

        mappingDto.setId( mapping.getId() );
        mappingDto.setStation( stationMapper.toDto( mapping.getStation() ) );
        mappingDto.setTrack( trackMapper.toDto( mapping.getTrack() ) );
        mappingDto.setStationOrder( mapping.getStationOrder() );

        return mappingDto;
    }

    @Override
    public List<MappingDto> toDtoList(List<Mappings> mappings) {
        if ( mappings == null ) {
            return null;
        }

        List<MappingDto> list = new ArrayList<MappingDto>( mappings.size() );
        for ( Mappings mappingEntity : mappings ) {
            list.add( toDto( mappingEntity ) );
        }

        return list;
    }

    @Override
    public Mappings toEntity(MappingDto mappingDto) {
        if ( mappingDto == null ) {
            return null;
        }

        Mappings mappingEntity = new Mappings();

        mappingEntity.setId( mappingDto.getId() );
        mappingEntity.setStation( stationMapper.toEntity( mappingDto.getStation() ) );
        mappingEntity.setTrack( trackMapper.toEntity( mappingDto.getTrack() ) );
        mappingEntity.setStationOrder( mappingDto.getStationOrder() );

        return mappingEntity;
    }
}
