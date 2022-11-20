package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : Volha Salash
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto implements Serializable {

    private int id;

    private StationDto station;

    private TrainDto train;

    private String trainStatus;

    private String arrivalTime;

    private String departureTime;

    private boolean direction;

    private String endStation;

    public ScheduleDto(String departureTime) {
        this.departureTime = departureTime;
    }
}