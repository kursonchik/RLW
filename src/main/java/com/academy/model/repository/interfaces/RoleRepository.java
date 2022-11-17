package com.academy.model.repository.interfaces;

import com.academy.model.entity.Roles;
import org.springframework.stereotype.Repository;

/**
 * @author : Volha Salash
 */

public interface RoleRepository {
    Roles findRoleByName(String name);
}