package com.academy.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author : Volha Salash
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String secondName;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private int role;

    @OneToMany(mappedBy = "users")
    private List<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getFirstName().equals(users.getFirstName()) &&
                getSecondName().equals(users.getSecondName()) &&
                getBirthDate().equals(users.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getSecondName(), getBirthDate());
    }
}