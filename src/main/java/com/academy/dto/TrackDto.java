package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto implements Serializable {

    private int id;

    private Set<TrainDto> trains;

    private Set<MappingDto> mappings;
}