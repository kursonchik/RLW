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

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private TrackMapper trackMapper;

    @Override
    public AreaDto toDto(Areas area) {
        if ( area == null ) {
            return null;
        }

        AreaDto areaDto = new AreaDto();

        areaDto.setId( area.getId() );
        areaDto.setStationFrom( stationMapper.toDto( area.getStationFrom() ) );
        areaDto.setStationTo( stationMapper.toDto( area.getStationTo() ) );
        areaDto.setDistance( area.getDistance() );
        areaDto.setTrack( trackMapper.toDto( area.getTrack() ) );
        areaDto.setDirection( area.isDirection() );

        return areaDto;
    }

    @Override
    public List<AreaDto> toDtoList(List<Areas> areas) {
        if ( areas == null ) {
            return null;
        }

        List<AreaDto> list = new ArrayList<AreaDto>( areas.size() );
        for ( Areas area : areas ) {
            list.add( toDto( area ) );
        }

        return list;
    }

    @Override
    public Areas toEntity(AreaDto areaDto) {
        if ( areaDto == null ) {
            return null;
        }

        Areas areaEntity = new Areas();

        areaEntity.setId( areaDto.getId() );
        areaEntity.setStationFrom( stationMapper.toEntity( areaDto.getStationFrom() ) );
        areaEntity.setStationTo( stationMapper.toEntity( areaDto.getStationTo() ) );
        areaEntity.setDistance( areaDto.getDistance() );
        areaEntity.setTrack( trackMapper.toEntity( areaDto.getTrack() ) );
        areaEntity.setDirection( areaDto.isDirection() );

        return areaEntity;
    }
}
