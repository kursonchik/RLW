package com.academy.mapper;

import com.academy.dto.ScheduleDto;
import com.academy.model.entity.Schedules;
import org.mapstruct.*;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ScheduleMapper {

    @Mapping(source = "arrivalTime", target = "arrivalTime", dateFormat = "HH:mm")
    @Mapping(source = "departureTime", target = "departureTime", dateFormat = "HH:mm")
    ScheduleDto toDto(Schedules schedule);

    @InheritConfiguration
    List<ScheduleDto> toDtoList(List<Schedules> schedules);

    @InheritInverseConfiguration
    Schedules toEntity(ScheduleDto scheduleDto);

    @InheritInverseConfiguration
    List<Schedules> toEntityList(List<ScheduleDto> scheduleDtoList);
}