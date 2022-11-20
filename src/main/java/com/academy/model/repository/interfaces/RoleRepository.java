package com.academy.model.repository.interfaces;

import com.academy.model.entity.Roles;

/**
 * @author : Volha Salash
 */

public interface RoleRepository {
    Roles findRoleByName(String name);
}