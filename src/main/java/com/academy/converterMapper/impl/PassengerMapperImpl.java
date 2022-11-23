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

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public PassengerDto toDto(Passengers passenger) {
        if ( passenger == null ) {
            return null;
        }

        PassengerDto passengerDto = new PassengerDto();

        if ( passenger.getBirthDate() != null ) {
            passengerDto.setBirthDate( new SimpleDateFormat( "dd.MM.yyyy" ).format( passenger.getBirthDate() ) );
        }
        passengerDto.setId( passenger.getId() );
        passengerDto.setFirstName( passenger.getFirstName() );
        passengerDto.setLastName( passenger.getLastName() );
        passengerDto.setPassportNumber( passenger.getPassportNumber() );
        passengerDto.setUser( userMapper.toDto( passenger.getUser() ) );
        passengerDto.setTickets( ticketEntitySetToTicketDtoSet( passenger.getTickets() ) );

        return passengerDto;
    }

    @Override
    public List<PassengerDto> toDtoList(List<Passengers> passengers) {
        if ( passengers == null ) {
            return null;
        }

        List<PassengerDto> list = new ArrayList<PassengerDto>( passengers.size() );
        for ( Passengers passengerEntity : passengers ) {
            list.add( toDto( passengerEntity ) );
        }

        return list;
    }

    @Override
    public Passengers toEntity(PassengerDto passengerDto) {
        if ( passengerDto == null ) {
            return null;
        }

        Passengers passengerEntity = new Passengers();

        try {
            if ( passengerDto.getBirthDate() != null ) {
                passengerEntity.setBirthDate( new SimpleDateFormat( "dd.MM.yyyy" ).parse( passengerDto.getBirthDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        passengerEntity.setId( passengerDto.getId() );
        passengerEntity.setFirstName( passengerDto.getFirstName() );
        passengerEntity.setLastName( passengerDto.getLastName() );
        passengerEntity.setPassportNumber( passengerDto.getPassportNumber() );
        passengerEntity.setUser( userMapper.toEntity( passengerDto.getUser() ) );
        passengerEntity.setTickets( ticketDtoSetToTicketEntitySet( passengerDto.getTickets() ) );

        return passengerEntity;
    }

    protected Set<TicketDto> ticketEntitySetToTicketDtoSet(Set<Tickets> set) {
        if ( set == null ) {
            return null;
        }

        Set<TicketDto> set1 = new HashSet<TicketDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tickets ticketEntity : set ) {
            set1.add( ticketMapper.toDto( ticketEntity ) );
        }

        return set1;
    }

    protected Set<Tickets> ticketDtoSetToTicketEntitySet(Set<TicketDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tickets> set1 = new HashSet<Tickets>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TicketDto ticketDto : set ) {
            set1.add( ticketMapper.toEntity( ticketDto ) );
        }

        return set1;
    }
}
