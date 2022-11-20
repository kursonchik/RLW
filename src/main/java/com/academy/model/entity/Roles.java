package com.academy.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Entity
@Data
public class Roles {
    //    implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

/*
    @Override
    public String getAuthority() {
        return getName();
    }

 */
}