package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.ScheduleMapper;
import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.ScheduleDto;
import com.academy.model.entity.Schedules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private TrainMapper trainMapper;

    @Override
    public ScheduleDto toDto(Schedules schedule) {
        if (schedule == null) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        if (schedule.getArrivalTime() != null) {
            scheduleDto.setArrivalTime(new SimpleDateFormat("HH:mm").format(schedule.getArrivalTime()));
        }
        if (schedule.getDepartureTime() != null) {
            scheduleDto.setDepartureTime(new SimpleDateFormat("HH:mm").format(schedule.getDepartureTime()));
        }
        scheduleDto.setId(schedule.getId());
        scheduleDto.setStation(stationMapper.toDto(schedule.getStation()));
        scheduleDto.setTrain(trainMapper.toDto(schedule.getTrain()));
        scheduleDto.setTrainStatus(schedule.getTrainStatus());
        scheduleDto.setDirection(schedule.isDirection());

        return scheduleDto;
    }

    @Override
    public List<ScheduleDto> toDtoList(List<Schedules> schedules) {
        if (schedules == null) {
            return null;
        }

        List<ScheduleDto> list = new ArrayList<ScheduleDto>(schedules.size());
        for (Schedules scheduleEntity : schedules) {
            list.add(toDto(scheduleEntity));
        }

        return list;
    }

    @Override
    public Schedules toEntity(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }

        Schedules scheduleEntity = new Schedules();

        try {
            if (scheduleDto.getArrivalTime() != null) {
                scheduleEntity.setArrivalTime(new SimpleDateFormat("HH:mm").parse(scheduleDto.getArrivalTime()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            if (scheduleDto.getDepartureTime() != null) {
                scheduleEntity.setDepartureTime(new SimpleDateFormat("HH:mm").parse(scheduleDto.getDepartureTime()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        scheduleEntity.setId(scheduleDto.getId());
        scheduleEntity.setStation(stationMapper.toEntity(scheduleDto.getStation()));
        scheduleEntity.setTrainStatus(scheduleDto.getTrainStatus());
        scheduleEntity.setTrain(trainMapper.toEntity(scheduleDto.getTrain()));
        scheduleEntity.setDirection(scheduleDto.isDirection());

        return scheduleEntity;
    }

    @Override
    public List<Schedules> toEntityList(List<ScheduleDto> scheduleDtoList) {
        if (scheduleDtoList == null) {
            return null;
        }

        List<Schedules> list = new ArrayList<Schedules>(scheduleDtoList.size());
        for (ScheduleDto scheduleDto : scheduleDtoList) {
            list.add(toEntity(scheduleDto));
        }

        return list;
    }
}
