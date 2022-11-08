package com.academy.model.repository.interfaces;

import com.academy.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Volha Salash
 */
public interface RoleRepository {
    Role findRoleByName(int id);
}