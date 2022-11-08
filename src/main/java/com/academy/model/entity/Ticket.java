package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Volha Salash
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Train train;

    @ManyToOne
    private Users users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getId() == ticket.getId() &&
                Objects.equals(getTrain(), ticket.getTrain()) &&
                Objects.equals(this.getUsers(), ticket.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTrain(), this.getUsers());
    }
}