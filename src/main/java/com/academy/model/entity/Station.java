package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nameRU")
    private String nameRU;

    @OneToMany(mappedBy = "originStation")
    private List<Train> trainsDeparture;

    @OneToMany(mappedBy = "destinationStation")
    private List<Train> trainsArrive;

    @OneToMany(mappedBy = "station")
    private List<Schedule> schedules;
}