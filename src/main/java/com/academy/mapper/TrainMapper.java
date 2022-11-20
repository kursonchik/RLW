package com.academy.mapper;

import com.academy.dto.TrainDto;
import com.academy.model.entity.Trains;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrainMapper {
    TrainMapper INSTANCE = Mappers.getMapper(TrainMapper.class);

    TrainDto toDto(Trains train);

    List<TrainDto> toDtoList(List<Trains> trains);

    Trains toEntity(TrainDto trainDto);
}