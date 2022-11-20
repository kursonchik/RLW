package com.academy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : Volha Salash
 */
@Data
public class MappingDto implements Serializable {

    private int id;

    private StationDto station;

    private TrackDto track;

    private int stationOrder;
}