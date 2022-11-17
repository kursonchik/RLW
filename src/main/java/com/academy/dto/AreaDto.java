package com.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author : Volha Salash
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AreaDto implements Serializable {

    private int id;

    @NotNull(message = "Set departure station")
    private StationDto stationFrom;

    @NotNull(message = "Set destination station")
    private StationDto stationTo;

    @Min(value = 1, message = "Distance between two stations can not less then 1")
    private double distance;

    private TrackDto track;

    private boolean direction;
}
