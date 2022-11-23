package com.academy.mapper;
/*
import com.academy.dto.TicketDto;
import com.academy.model.entity.Tickets;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Volha Salash
 */
/*
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

    /**
     * To dto ticket dto.
     *
     * @param ticket the ticket entity
     * @return the ticket dto
     */
/*
    @Mapping(source = "departureTime", target = "departureTime", dateFormat = "HH:mm")
    @Mapping(source = "arrivalTime", target = "arrivalTime", dateFormat = "HH:mm")
    @Mapping(source = "date", target = "date", dateFormat = "dd.MM.yyyy")
    TicketDto toDto(Tickets ticket);

    /**
     * To dto list list.
     *
     * @param tickets the ticket entity list
     * @return the list
     */
/*
    @InheritConfiguration
    List<TicketDto> toDtoList(List<Tickets> tickets);

    /**
     * To entity ticket entity.
     *
     * @param ticketDto the ticket dto
     * @return the ticket entity
     */
/*
    @Mappings({

            @Mapping(target = "passenger", source = "ticketDto.passenger"),
            @Mapping(target = "trains", source = "ticketDto.trains")
    })
    @InheritInverseConfiguration
    Tickets toEntity(TicketDto ticketDto);
}

 */