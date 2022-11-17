package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Tracks implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "track")
    private Set<Trains> trains;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "track")
    private Set<Mappings> mappings;
}