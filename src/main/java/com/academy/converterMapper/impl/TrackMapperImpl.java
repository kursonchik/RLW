package com.academy.converterMapper.impl;

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

    private final TrainMapper trainMapper;

    @Autowired
    public TrackMapperImpl(TrainMapper trainMapper) {
        this.trainMapper = trainMapper;
    }

    @Override
    public TrackDto toDto(Tracks track) {
        if (track == null) {
            return null;
        }

        TrackDto trackDto = new TrackDto();

        trackDto.setId(track.getId());

        return trackDto;
    }

    @Override
    public List<TrackDto> toDtoList(List<Tracks> tracks) {
        if (tracks == null) {
            return null;
        }

        List<TrackDto> list = new ArrayList<TrackDto>(tracks.size());
        for (Tracks tracks1 : tracks) {
            list.add(toDto(tracks1));
        }

        return list;
    }

    @Override
    public Tracks toEntity(TrackDto trackDto) {
        if (trackDto == null) {
            return null;
        }

        Tracks tracks = new Tracks();

        tracks.setId(trackDto.getId());
        tracks.setTrains(trainDtoSetToTrainsSet(trackDto.getTrains()));
        tracks.setMappings(mappingDtoSetToMappingsSet(trackDto.getMappings()));

        return tracks;
    }

    protected Set<TrainDto> trainsSetToTrainDtoSet(Set<Trains> set) {
        if (set == null) {
            return null;
        }

        Set<TrainDto> set1 = new HashSet<TrainDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Trains trains : set) {
            set1.add(trainMapper.toDto(trains));
        }

        return set1;
    }

    protected ScheduleDto schedulesToScheduleDto(Schedules schedules) {
        if (schedules == null) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(schedules.getId());
        scheduleDto.setStation(stationsToStationDto(schedules.getStation()));
        scheduleDto.setTrain(trainMapper.toDto(schedules.getTrain()));
        scheduleDto.setTrainStatus(schedules.getTrainStatus());
        if (schedules.getArrivalTime() != null) {
            scheduleDto.setArrivalTime(new SimpleDateFormat().format(schedules.getArrivalTime()));
        }
        if (schedules.getDepartureTime() != null) {
            scheduleDto.setDepartureTime(new SimpleDateFormat().format(schedules.getDepartureTime()));
        }
        scheduleDto.setDirection(schedules.isDirection());

        return scheduleDto;
    }

    protected Set<ScheduleDto> schedulesSetToScheduleDtoSet(Set<Schedules> set) {
        if (set == null) {
            return null;
        }

        Set<ScheduleDto> set1 = new HashSet<ScheduleDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Schedules schedules : set) {
            set1.add(schedulesToScheduleDto(schedules));
        }

        return set1;
    }

    protected Set<MappingDto> mappingsSetToMappingDtoSet(Set<Mappings> set) {
        if (set == null) {
            return null;
        }

        Set<MappingDto> set1 = new HashSet<MappingDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Mappings mappings : set) {
            set1.add(mappingsToMappingDto(mappings));
        }

        return set1;
    }

    protected StationDto stationsToStationDto(Stations stations) {
        if (stations == null) {
            return null;
        }

        StationDto stationDto = new StationDto();

        stationDto.setId(stations.getId());
        stationDto.setName((stations.getName()));
        stationDto.setSchedules(schedulesSetToScheduleDtoSet(stations.getSchedules()));
        stationDto.setMappings(mappingsSetToMappingDtoSet(stations.getMappings()));

        return stationDto;
    }

    protected MappingDto mappingsToMappingDto(Mappings mappings) {
        if (mappings == null) {
            return null;
        }

        MappingDto mappingDto = new MappingDto();

        mappingDto.setId(mappings.getId());
        mappingDto.setStation(stationsToStationDto(mappings.getStation()));
        mappingDto.setTrack(toDto(mappings.getTrack()));
        mappingDto.setStationOrder(mappings.getStationOrder());

        return mappingDto;
    }

    protected Set<Trains> trainDtoSetToTrainsSet(Set<TrainDto> set) {
        if (set == null) {
            return null;
        }

        Set<Trains> set1 = new HashSet<Trains>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TrainDto trainDto : set) {
            set1.add(trainMapper.toEntity(trainDto));
        }

        return set1;
    }

    protected Schedules scheduleDtoToSchedules(ScheduleDto scheduleDto) throws ParseException {
        if (scheduleDto == null) {
            return null;
        }

        Schedules schedules = new Schedules();

        schedules.setId(scheduleDto.getId());
        schedules.setStation(stationDtoToStations(scheduleDto.getStation()));
        schedules.setTrainStatus(scheduleDto.getTrainStatus());
        schedules.setTrain(trainMapper.toEntity(scheduleDto.getTrain()));
        if (scheduleDto.getArrivalTime() != null) {
            schedules.setArrivalTime(new SimpleDateFormat().parse(scheduleDto.getArrivalTime()));
        }
        if (scheduleDto.getDepartureTime() != null) {
            schedules.setDepartureTime(new SimpleDateFormat().parse(scheduleDto.getDepartureTime()));
        }
        schedules.setDirection(scheduleDto.isDirection());

        return schedules;
    }

    protected Set<Schedules> scheduleDtoSetToSchedulesSet(Set<ScheduleDto> set) throws ParseException {
        if (set == null) {
            return null;
        }

        Set<Schedules> set1 = new HashSet<Schedules>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (ScheduleDto scheduleDto : set) {
            set1.add(scheduleDtoToSchedules(scheduleDto));
        }

        return set1;
    }

    protected Set<Mappings> mappingDtoSetToMappingsSet(Set<MappingDto> set) {
        if (set == null) {
            return null;
        }

        Set<Mappings> set1 = new HashSet<Mappings>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (MappingDto mappingDto : set) {
            try {
                set1.add(mappingDtoToMappings(mappingDto));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return set1;
    }

    protected Stations stationDtoToStations(StationDto stationDto) throws ParseException {
        if (stationDto == null) {
            return null;
        }

        Stations stations = new Stations();

        stations.setId(stationDto.getId());
        stations.setName(stationDto.getName());
        stations.setEndpoint(stationDto.isEndpoint());
        stations.setBreakpoint(stationDto.isBreakpoint());
        stations.setSchedules(scheduleDtoSetToSchedulesSet(stationDto.getSchedules()));
        stations.setMappings(mappingDtoSetToMappingsSet(stationDto.getMappings()));

        return stations;
    }

    protected Mappings mappingDtoToMappings(MappingDto mappingDto) throws ParseException {
        if (mappingDto == null) {
            return null;
        }

        Mappings mappings = new Mappings();

        mappings.setId(mappingDto.getId());
        mappings.setStation(stationDtoToStations(mappingDto.getStation()));
        mappings.setTrack(toEntity(mappingDto.getTrack()));
        mappings.setStationOrder(mappingDto.getStationOrder());

        return mappings;
    }
}
