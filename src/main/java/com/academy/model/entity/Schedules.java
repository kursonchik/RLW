package com.academy.model.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Volha Salash
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Schedules implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;

        @ToString.Exclude
        @ManyToOne(cascade = {CascadeType.PERSIST})
        @JoinColumn(name = "station_id")
        private Stations station;

        @Column(name = "train_status")
        private String trainStatus;

        @ToString.Exclude
        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "train_id")
        private Trains train;

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(pattern = "HH:mm")
        @Column(name = "arrival_time")
        private Date arrivalTime;

        @Temporal(TemporalType.TIME)
        @DateTimeFormat(pattern = "HH:mm")
        @Column(name = "departure_time")
        private Date departureTime;

        @Column(name = "direction")
        private boolean direction;
    }
