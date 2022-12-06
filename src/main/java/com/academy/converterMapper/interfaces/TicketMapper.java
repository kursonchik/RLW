package com.academy.converterMapper.interfaces;

import com.academy.dto.TicketDto;
import com.academy.model.entity.Tickets;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TicketMapper {

    TicketDto toDto(Tickets ticket);

    List<TicketDto> toDtoList(List<Tickets> tickets);

    Tickets toEntity(TicketDto ticketDto);
}