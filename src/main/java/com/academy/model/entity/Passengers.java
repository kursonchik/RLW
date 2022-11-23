package com.academy.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author : Volha Salash
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Passengers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Past
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "birth_date")
    private Date birthDate;

    @Max(value = 99999999)
    @Column(name = "passport_number")
    private int passportNumber;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    private Users user;

    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "passenger", cascade = CascadeType.ALL)
    private Set<Tickets> tickets;
}