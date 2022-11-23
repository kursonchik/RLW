package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.PassengerMapper;
import com.academy.converterMapper.interfaces.TicketMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.*;
import com.academy.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class TicketMapperImpl implements TicketMapper {

    @Autowired
    private PassengerMapper passengerMapper;
    @Autowired
    private TrainMapper trainMapper;

    @Override
    public TicketDto toDto(Tickets ticket) {
        if ( ticket == null ) {
            return null;
        }

        TicketDto ticketDto = new TicketDto();

        if ( ticket.getDepartureTime() != null ) {
            ticketDto.setDepartureTime( new SimpleDateFormat( "HH:mm" ).format( ticket.getDepartureTime() ) );
        }
        if ( ticket.getArrivalTime() != null ) {
            ticketDto.setArrivalTime( new SimpleDateFormat( "HH:mm" ).format( ticket.getArrivalTime() ) );
        }
        if ( ticket.getDate() != null ) {
            ticketDto.setDate( new SimpleDateFormat( "dd.MM.yyyy" ).format( ticket.getDate() ) );
        }
        ticketDto.setId( ticket.getId() );
        ticketDto.setNumber( ticket.getNumber() );
        ticketDto.setPassenger( passengerMapper.toDto( ticket.getPassenger() ) );
        ticketDto.setTrains( trainEntitySetToTrainDtoSet( ticket.getTrains() ) );
        ticketDto.setDepartureStation( ticket.getDepartureStation() );
        ticketDto.setArrivalStation( ticket.getArrivalStation() );
        ticketDto.setPrice( ticket.getPrice() );

        return ticketDto;
    }

    @Override
    public List<TicketDto> toDtoList(List<Tickets> tickets) {
        if ( tickets == null ) {
            return null;
        }

        List<TicketDto> list = new ArrayList<TicketDto>( tickets.size() );
        for ( Tickets ticketEntity : tickets ) {
            list.add( toDto( ticketEntity ) );
        }

        return list;
    }

    @Override
    public Tickets toEntity(TicketDto ticketDto) {
        if ( ticketDto == null ) {
            return null;
        }

        Tickets ticketEntity = new Tickets();

        try {
            if ( ticketDto.getDepartureTime() != null ) {
                ticketEntity.setDepartureTime( new SimpleDateFormat( "HH:mm" ).parse( ticketDto.getDepartureTime() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( ticketDto.getArrivalTime() != null ) {
                ticketEntity.setArrivalTime( new SimpleDateFormat( "HH:mm" ).parse( ticketDto.getArrivalTime() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( ticketDto.getDate() != null ) {
                ticketEntity.setDate( new SimpleDateFormat( "dd.MM.yyyy" ).parse( ticketDto.getDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        ticketEntity.setId( ticketDto.getId() );
        ticketEntity.setNumber( ticketDto.getNumber() );
        ticketEntity.setPassenger( passengerMapper.toEntity( ticketDto.getPassenger() ) );
        ticketEntity.setTrains( trainDtoSetToTrainEntitySet( ticketDto.getTrains() ) );
        ticketEntity.setDepartureStation( ticketDto.getDepartureStation() );
        ticketEntity.setArrivalStation( ticketDto.getArrivalStation() );
        ticketEntity.setPrice( ticketDto.getPrice() );

        return ticketEntity;
    }

    protected Set<TrainDto> trainEntitySetToTrainDtoSet(Set<Trains> set) {
        if ( set == null ) {
            return null;
        }

        Set<TrainDto> set1 = new HashSet<TrainDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Trains trainEntity : set ) {
            set1.add( trainMapper.toDto( trainEntity ) );
        }

        return set1;
    }

    protected Set<Trains> trainDtoSetToTrainEntitySet(Set<TrainDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Trains> set1 = new HashSet<Trains>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TrainDto trainDto : set ) {
            set1.add( trainMapper.toEntity( trainDto ) );
        }

        return set1;
    }
}