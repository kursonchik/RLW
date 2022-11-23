package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.MappingMapper;
import com.academy.converterMapper.interfaces.TrackMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
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
public class TrackMapperImpl implements TrackMapper {

    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private MappingMapper mappingMapper;

    @Override
    public TrackDto toDto(Tracks track) {
        if ( track == null ) {
            return null;
        }

        TrackDto trackDto = new TrackDto();

        trackDto.setId( track.getId() );
        trackDto.setTrains( trainEntitySetToTrainDtoSet( track.getTrains() ) );
        trackDto.setMappings( mappingEntitySetToMappingDtoSet( track.getMappings() ) );

        return trackDto;
    }

    @Override
    public List<TrackDto> toDtoList(List<Tracks> tracks) {
        if ( tracks == null ) {
            return null;
        }

        List<TrackDto> list = new ArrayList<TrackDto>( tracks.size() );
        for ( Tracks trackEntity : tracks ) {
            list.add( toDto( trackEntity ) );
        }

        return list;
    }

    @Override
    public Tracks toEntity(TrackDto trackDto) {
        if ( trackDto == null ) {
            return null;
        }

        Tracks trackEntity = new Tracks();

        trackEntity.setId( trackDto.getId() );
        trackEntity.setTrains( trainDtoSetToTrainEntitySet( trackDto.getTrains() ) );
        trackEntity.setMappings( mappingDtoSetToMappingEntitySet( trackDto.getMappings() ) );

        return trackEntity;
    }

    protected Set<TrainDto> trainEntitySetToTrainDtoSet(Set<Trains> set) {
        if ( set == null ) {
            return null;
        }

        Set<TrainDto> set1 = new HashSet<TrainDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Trains trainEntity : set ) {
            set1.add( trainMapper.toDto( trainEntity ) );
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

    protected Set<Trains> trainDtoSetToTrainEntitySet(Set<TrainDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Trains> set1 = new HashSet<Trains>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TrainDto trainDto : set ) {
            set1.add( trainMapper.toEntity( trainDto ) );
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
