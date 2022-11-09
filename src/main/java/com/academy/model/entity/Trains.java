package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trains implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Min(value = 10)
    @Max(value = 100)
    @Column(name = "seats")
    private int seats;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "train")
    private Set<Schedules> schedules;

    @ToString.Exclude
    @Transient
    @ManyToMany(mappedBy = "trains")
    private Set<Tickets> tickets;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "root_id")
    private Roots root;
}