package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : Volha Salash
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Mappings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private Stations station;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "track_id")
    private Tracks track;

    @Column(name = "station_order")
    private int stationOrder;

    public Mappings(Stations station, Tracks track, int stationOrder) {
        this.station = station;
        this.track = track;
        this.stationOrder = stationOrder;
    }
}
