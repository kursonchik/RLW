package com.academy.converterMapper.interfaces;

import com.academy.dto.TrainDto;
import com.academy.model.entity.Trains;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TrainMapper {

    TrainDto toDto(Trains train);

    List<TrainDto> toDtoList(List<Trains> trains);

    Trains toEntity(TrainDto trainDto);
}