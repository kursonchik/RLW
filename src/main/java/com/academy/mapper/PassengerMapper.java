package com.academy.mapper;

import com.academy.dto.PassengerDto;
import com.academy.model.entity.Passengers;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(uses = {UserMapper.class, TicketMapper.class})
public interface PassengerMapper {

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd.MM.yyyy")
    PassengerDto toDto(Passengers passenger);

    @InheritConfiguration
    List<PassengerDto> toDtoList(List<Passengers> passengers);

    @InheritInverseConfiguration
    Passengers toEntity(PassengerDto passengerDto);
}