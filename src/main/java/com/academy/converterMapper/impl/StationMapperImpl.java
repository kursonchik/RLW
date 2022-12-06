package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.MappingMapper;
import com.academy.converterMapper.interfaces.ScheduleMapper;
import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.dto.*;
import com.academy.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class StationMapperImpl implements StationMapper {

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private MappingMapper mappingMapper;

    @Override
    public StationDto toDto(Stations station) {
        if ( station == null ) {
            return null;
        }

        StationDto stationDto = new StationDto();

        stationDto.setId( station.getId() );
        stationDto.setName( station.getName() );
        stationDto.setEndpoint( station.isEndpoint() );
        stationDto.setBreakpoint( station.isBreakpoint() );
        stationDto.setSchedules( scheduleEntitySetToScheduleDtoSet( station.getSchedules() ) );
        stationDto.setMappings( mappingEntitySetToMappingDtoSet( station.getMappings() ) );

        return stationDto;
    }

    @Override
    public List<StationDto> toDtoList(List<Stations> stations) {
        if ( stations == null ) {
            return null;
        }

        List<StationDto> list = new ArrayList<StationDto>( stations.size() );
        for ( Stations stationEntity : stations ) {
            list.add( toDto( stationEntity ) );
        }

        return list;
    }

    @Override
    public Stations toEntity(StationDto trainDto) {
        if ( trainDto == null ) {
            return null;
        }

        Stations stationEntity = new Stations();

        stationEntity.setId( trainDto.getId() );
        stationEntity.setName( trainDto.getName() );
        stationEntity.setEndpoint( trainDto.isEndpoint() );
        stationEntity.setBreakpoint( trainDto.isBreakpoint() );
        stationEntity.setSchedules( scheduleDtoSetToScheduleEntitySet( trainDto.getSchedules() ) );
        stationEntity.setMappings( mappingDtoSetToMappingEntitySet( trainDto.getMappings() ) );

        return stationEntity;
    }

    @Override
    public List<Stations> toEntityList(List<StationDto> stationDtoList) {
        if ( stationDtoList == null ) {
            return null;
        }

        List<Stations> list = new ArrayList<Stations>( stationDtoList.size() );
        for ( StationDto stationDto : stationDtoList ) {
            list.add( toEntity( stationDto ) );
        }

        return list;
    }

    protected Set<ScheduleDto> scheduleEntitySetToScheduleDtoSet(Set<Schedules> set) {
        if ( set == null ) {
            return null;
        }

        Set<ScheduleDto> set1 = new HashSet<ScheduleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Schedules scheduleEntity : set ) {
            set1.add( scheduleMapper.toDto( scheduleEntity ) );
        }

        return set1;
    }

    protected Set<MappingDto> mappingEntitySetToMappingDtoSet(Set<Mappings> set) {
        if ( set == null ) {
            return null;
        }

        Set<MappingDto> set1 = new HashSet<MappingDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Mappings mappingEntity : set ) {
            set1.add( mappingMapper.toDto( mappingEntity ) );
        }

        return set1;
    }

    protected Set<Schedules> scheduleDtoSetToScheduleEntitySet(Set<ScheduleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Schedules> set1 = new HashSet<Schedules>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ScheduleDto scheduleDto : set ) {
            set1.add( scheduleMapper.toEntity( scheduleDto ) );
        }

        return set1;
    }

    protected Set<Mappings> mappingDtoSetToMappingEntitySet(Set<MappingDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Mappings> set1 = new HashSet<Mappings>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MappingDto mappingDto : set ) {
            set1.add( mappingMapper.toEntity( mappingDto ) );
        }

        return set1;
    }
}
