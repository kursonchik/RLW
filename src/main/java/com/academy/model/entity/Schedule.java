package com.academy.model.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : Volha Salash
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "arriveTime")
    private LocalDateTime arrivalTime;

    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "trainID")
    Train train;

    @ManyToOne
    @JoinColumn(name = "stationID")
    Station station;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return getId() == schedule.getId() &&
                Objects.equals(getArrivalTime(), schedule.getArrivalTime()) &&
                Objects.equals(getDepartureTime(), schedule.getDepartureTime()) &&
                Objects.equals(getTrain(), schedule.getTrain()) &&
                Objects.equals(getStation(), schedule.getStation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getArrivalTime(), getDepartureTime(), getTrain(), getStation());
    }
}
