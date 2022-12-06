package com.academy.converterMapper.interfaces;

import com.academy.dto.PassengerDto;
import com.academy.model.entity.Passengers;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface PassengerMapper {

    PassengerDto toDto(Passengers passenger);

    List<PassengerDto> toDtoList(List<Passengers> passengers);

    Passengers toEntity(PassengerDto passengerDto);
}