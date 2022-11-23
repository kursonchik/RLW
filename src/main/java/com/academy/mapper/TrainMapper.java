package com.academy.mapper;
/*
import com.academy.dto.TrainDto;
import com.academy.model.entity.Trains;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : Volha Salash
 */
/*
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TrainMapper {
    TrainMapper INSTANCE = Mappers.getMapper(TrainMapper.class);
    /**
     * To dto train dto.
     *
     * @param train the train entity
     * @return the train dto
     */
/*
    TrainDto toDto(Trains train);

    /**
     * To dto list list.
     *
     * @param trains the train entity list
     * @return the list
     */
/*
    List<TrainDto> toDtoList(List<Trains> trains);

    /**
     * To entity train entity.
     *
     * @param trainDto the train dto
     * @return the train entity
     */
/*
    @Mappings({
            @Mapping(target = "tickets", ignore = true),
            @Mapping(target = "schedules", ignore = true),
            @Mapping(target = "track", ignore = true),
    })
    Trains toEntity(TrainDto trainDto);
}

 */