package com.academy.mapper;

import com.academy.dto.PassengerDto;
import com.academy.model.entity.Passengers;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PassengerMapper {
    PassengerMapper INSTANCE = Mappers.getMapper(PassengerMapper.class);
    /**
     * To dto passenger dto.
     *
     * @param passenger the passenger entity
     * @return the passenger dto
     */
    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "dd.MM.yyyy")
    PassengerDto toDto(Passengers passenger);

    /**
     * To dto list list.
     *
     * @param passengers the passenger entity list
     * @return the list
     */

    @InheritConfiguration
    List<PassengerDto> toDtoList(List<Passengers> passengers);

    /**
     * To entity list list.
     *
     * @param passengerDto the passenger dto list
     * @return the list
     */
    @InheritInverseConfiguration
    Passengers toEntity(PassengerDto passengerDto);
}