package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.PassengerMapper;
import com.academy.converterMapper.interfaces.TicketMapper;
import com.academy.converterMapper.interfaces.UserMapper;
import com.academy.dto.PassengerDto;
import com.academy.dto.TicketDto;
import com.academy.model.entity.Passengers;
import com.academy.model.entity.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Component
public class PassengerMapperImpl implements PassengerMapper {

    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    @Autowired
    public PassengerMapperImpl(UserMapper userMapper, TicketMapper ticketMapper) {

        this.userMapper = userMapper;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public PassengerDto toDto(Passengers passenger) {
        if (passenger == null) {
            return null;
        }

        PassengerDto.PassengerDtoBuilder passengerDto = PassengerDto.builder();

        if (passenger.getBirthDate() != null) {
            passengerDto.birthDate(new SimpleDateFormat("dd.MM.yyyy").format(passenger.getBirthDate()));
        }
        passengerDto.id(passenger.getId());
        passengerDto.firstName(passenger.getFirstName());
        passengerDto.lastName(passenger.getLastName());
        passengerDto.passportNumber(passenger.getPassportNumber());
        passengerDto.user(userMapper.toDto(passenger.getUser()));
        passengerDto.tickets(ticketsSetToTicketDtoSet(passenger.getTickets()));

        return passengerDto.build();
    }

    @Override
    public List<PassengerDto> toDtoList(List<Passengers> passengers) {
        if (passengers == null) {
            return null;
        }

        List<PassengerDto> list = new ArrayList<PassengerDto>(passengers.size());
        for (Passengers passengers1 : passengers) {
            list.add(toDto(passengers1));
        }

        return list;
    }

    @Override
    public Passengers toEntity(PassengerDto passengerDto) {
        if (passengerDto == null) {
            return null;
        }

        Passengers passengers = new Passengers();

        try {
            if (passengerDto.getBirthDate() != null) {
                passengers.setBirthDate(new SimpleDateFormat("dd.MM.yyyy").parse(passengerDto.getBirthDate()));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        passengers.setId(passengerDto.getId());
        passengers.setFirstName(passengerDto.getFirstName());
        passengers.setLastName(passengerDto.getLastName());
        passengers.setPassportNumber(passengerDto.getPassportNumber());
        passengers.setUser(userMapper.toEntity(passengerDto.getUser()));
        passengers.setTickets(ticketDtoSetToTicketsSet(passengerDto.getTickets()));

        return passengers;
    }

    protected Set<TicketDto> ticketsSetToTicketDtoSet(Set<Tickets> set) {
        if (set == null) {
            return null;
        }

        Set<TicketDto> set1 = new HashSet<TicketDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Tickets tickets : set) {
            set1.add(ticketMapper.toDto(tickets));
        }

        return set1;
    }

    protected Set<Tickets> ticketDtoSetToTicketsSet(Set<TicketDto> set) {
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