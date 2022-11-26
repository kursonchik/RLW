package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Volha Salash
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "areas")
public class Areas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "station_from_id")
    private Stations stationFrom;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "station_to_id")
    private Stations stationTo;

    @Column(name = "distance")
    private double distance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "track_id")
    private Tracks track;

    @Column(name = "direction")
    private boolean direction;


    public Areas(Stations stationFrom, Stations stationTo,
                 double distance, Tracks track, boolean direction) {
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.distance = distance;
        this.track = track;
        this.direction = direction;
    }
}
