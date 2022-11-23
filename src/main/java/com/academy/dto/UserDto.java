package com.academy.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private int id;

    private String username;

    private String email;

    private PassengerDto passenger;

    private String password;

    private String passwordConfirm;

    private Set<RoleDto> roles;
}
