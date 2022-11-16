package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stations implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "is_endpoint")
    private boolean isEndpoint;

    @Column(name = "is_breakpoint")
    private boolean isBreakpoint;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private Set<Schedules> schedules;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    private Set<Mappings> mappings;
}