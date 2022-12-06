package com.academy.converterMapper.interfaces;

import com.academy.dto.ScheduleDto;
import com.academy.model.entity.Schedules;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface ScheduleMapper {

    ScheduleDto toDto(Schedules schedule);

    List<ScheduleDto> toDtoList(List<Schedules> schedules);

    Schedules toEntity(ScheduleDto scheduleDto);

    List<Schedules> toEntityList(List<ScheduleDto> scheduleDtoList);
}