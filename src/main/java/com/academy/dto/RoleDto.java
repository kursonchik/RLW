package com.academy.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Data
public class RoleDto implements Serializable {

    private int id;

    private String name;

    private Set<UserDto> users;
}