package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Volha Salash
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MappingDto implements Serializable {

    private int id;

    private StationDto station;

    private TrackDto track;

    private int stationOrder;
}