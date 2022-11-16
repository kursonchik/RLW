package com.academy.mapper;

import com.academy.dto.TicketDto;
import com.academy.model.entity.Tickets;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {PassengerMapper.class, TrainMapper.class})
public interface TicketMapper {

    @Mapping(source = "departureTime", target = "departureTime", dateFormat = "HH:mm")
    @Mapping(source = "arrivalTime", target = "arrivalTime", dateFormat = "HH:mm")
    @Mapping(source = "date", target = "date", dateFormat = "dd.MM.yyyy")
    TicketDto toDto(Tickets ticket);

    @InheritConfiguration
    List<TicketDto> toDtoList(List<Tickets> tickets);

    @InheritInverseConfiguration
    Tickets toEntity(TicketDto ticketDto);
}