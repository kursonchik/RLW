package com.academy.mapper;

import com.academy.dto.TrainDto;
import com.academy.model.entity.Trains;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrainMapper {

    TrainDto toDto(Trains train);

    List<TrainDto> toDtoList(List<Trains> trains);

    Trains toEntity(TrainDto trainDto);
}