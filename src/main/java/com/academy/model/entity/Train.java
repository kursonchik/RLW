package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
public class Train implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;

    int seats;

    @OneToMany(mappedBy = "train")
    private List<Schedule> schedules;

    @ManyToOne
    @JoinColumn(name = "originStationID")
    private Station originStation;

    @ManyToOne
    @JoinColumn(name = "destinationStationID")
    private Station destinationStation;

    @OneToMany(mappedBy = "train")
    private List<Ticket> tickets;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Train)) return false;
        Train train = (Train) o;
        return getId() == train.getId() &&
                getNumber() == train.getNumber() &&
                getSeats() == train.getSeats() &&
                Objects.equals(getSchedules(), train.getSchedules()) &&
                Objects.equals(getOriginStation(), train.getOriginStation()) &&
                Objects.equals(getDestinationStation(), train.getDestinationStation()) &&
                Objects.equals(getTickets(), train.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getSeats(), getSchedules(), getOriginStation(), getDestinationStation(), getTickets());
    }
}