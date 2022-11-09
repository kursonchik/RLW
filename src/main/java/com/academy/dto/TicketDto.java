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
public class TicketDto implements Serializable {

    private int id;

    private String number;

    private PassengerDto passenger;

    private Set<TrainDto> trains;

    private String departureStation;

    private String arrivalStation;

    private String departureTime;

    private String arrivalTime;

    private String date;

    private double price;
}