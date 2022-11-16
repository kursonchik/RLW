package com.academy.model.repository.interfaces;

import com.academy.model.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * @author : Volha Salash
 */

public interface UserRepository {
    Users findUserByUsername(String username);

    Users findUserByEmail(String email);

    void addUser(Users user);
}