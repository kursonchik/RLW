package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.ScheduleMapper;
import com.academy.converterMapper.interfaces.TicketMapper;
import com.academy.converterMapper.interfaces.TrackMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.ScheduleDto;
import com.academy.dto.TicketDto;
import com.academy.dto.TrainDto;
import com.academy.model.entity.Schedules;
import com.academy.model.entity.Tickets;
import com.academy.model.entity.Trains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TrainMapperImpl implements TrainMapper {

    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TrackMapper trackMapper;

    @Override
    public TrainDto toDto(Trains train) {
        if (train == null) {
            return null;
        }

        TrainDto trainDto = new TrainDto();

        trainDto.setId(train.getId());
        trainDto.setName(train.getName());
        trainDto.setSeats(train.getSeats());
        trainDto.setSchedules(scheduleEntitySetToScheduleDtoSet(train.getSchedules()));
        trainDto.setTickets(ticketEntitySetToTicketDtoSet(train.getTickets()));
        trainDto.setTrack(trackMapper.toDto(train.getTrack()));

        return trainDto;
    }

    @Override
    public List<TrainDto> toDtoList(List<Trains> trains) {
        if (trains == null) {
            return null;
        }

        List<TrainDto> list = new ArrayList<TrainDto>(trains.size());
        for (Trains trainEntity : trains) {
            list.add(toDto(trainEntity));
        }

        return list;
    }

    @Override
    public Trains toEntity(TrainDto trainDto) {
        if (trainDto == null) {
            return null;
        }

        Trains trainEntity = new Trains();

        trainEntity.setId(trainDto.getId());
        trainEntity.setName(trainDto.getName());
        trainEntity.setSeats(trainDto.getSeats());
        trainEntity.setSchedules(scheduleDtoSetToScheduleEntitySet(trainDto.getSchedules()));
        trainEntity.setTickets(ticketDtoSetToTicketEntitySet(trainDto.getTickets()));
        trainEntity.setTrack(trackMapper.toEntity(trainDto.getTrack()));

        return trainEntity;
    }

    protected Set<ScheduleDto> scheduleEntitySetToScheduleDtoSet(Set<Schedules> set) {
        if (set == null) {
            return null;
        }

        Set<ScheduleDto> set1 = new HashSet<ScheduleDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Schedules scheduleEntity : set) {
            set1.add(scheduleMapper.toDto(scheduleEntity));
        }

        return set1;
    }

    protected Set<TicketDto> ticketEntitySetToTicketDtoSet(Set<Tickets> set) {
        if (set == null) {
            return null;
        }

        Set<TicketDto> set1 = new HashSet<TicketDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Tickets ticketEntity : set) {
            set1.add(ticketMapper.toDto(ticketEntity));
        }

        return set1;
    }

    protected Set<Schedules> scheduleDtoSetToScheduleEntitySet(Set<ScheduleDto> set) {
        if (set == null) {
            return null;
        }

        Set<Schedules> set1 = new HashSet<Schedules>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (ScheduleDto scheduleDto : set) {
            set1.add(scheduleMapper.toEntity(scheduleDto));
        }

        return set1;
    }

    protected Set<Tickets> ticketDtoSetToTicketEntitySet(Set<TicketDto> set) {
        if (set == null) {
            return null;
        }

        Set<Tickets> set1 = new HashSet<Tickets>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TicketDto ticketDto : set) {
            set1.add(ticketMapper.toEntity(ticketDto));
        }

        return set1;
    }
}
