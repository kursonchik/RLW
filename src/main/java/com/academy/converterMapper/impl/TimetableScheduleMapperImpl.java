package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.TimetableScheduleMapper;
import com.academy.dto.ScheduleDto;
import com.academy.dto.StationDto;
import com.academy.dto.TimetableScheduleDto;
import com.academy.dto.TrainDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimetableScheduleMapperImpl implements TimetableScheduleMapper {

    @Override
    public TimetableScheduleDto toDto(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }

        TimetableScheduleDto timetableScheduleDto = new TimetableScheduleDto();

        timetableScheduleDto.setStationName(scheduleDtoStationName(scheduleDto));
        timetableScheduleDto.setTrainName(scheduleDtoTrainName(scheduleDto));
        timetableScheduleDto.setId(scheduleDto.getId());
        timetableScheduleDto.setTrainStatus(scheduleDto.getTrainStatus());
        timetableScheduleDto.setArrivalTime(scheduleDto.getArrivalTime());
        timetableScheduleDto.setDepartureTime(scheduleDto.getDepartureTime());
        timetableScheduleDto.setDirection(scheduleDto.isDirection());
        timetableScheduleDto.setEndStation(scheduleDto.getEndStation());

        return timetableScheduleDto;
    }

    @Override
    public List<TimetableScheduleDto> toDtoList(List<ScheduleDto> scheduleDtoList) {
        if (scheduleDtoList == null) {
            return null;
        }

        List<TimetableScheduleDto> list = new ArrayList<TimetableScheduleDto>(scheduleDtoList.size());
        for (ScheduleDto scheduleDto : scheduleDtoList) {
            list.add(toDto(scheduleDto));
        }

        return list;
    }

    private String scheduleDtoStationName(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }
        StationDto station = scheduleDto.getStation();
        if (station == null) {
            return null;
        }
        String name = station.getName();
        if (name == null) {
            return null;
        }
        return name;
    }

    private String scheduleDtoTrainName(ScheduleDto scheduleDto) {
        if (scheduleDto == null) {
            return null;
        }
        TrainDto train = scheduleDto.getTrain();
        if (train == null) {
            return null;
        }
        String name = train.getName();
        if (name == null) {
            return null;
        }
        return name;
    }
}
