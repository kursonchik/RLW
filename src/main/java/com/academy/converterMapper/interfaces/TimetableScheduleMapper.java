package com.academy.converterMapper.interfaces;

import com.academy.dto.ScheduleDto;
import com.academy.dto.TimetableScheduleDto;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TimetableScheduleMapper {

    TimetableScheduleDto toDto(ScheduleDto scheduleDto);

    List<TimetableScheduleDto> toDtoList(List<ScheduleDto> scheduleDtoList);
}