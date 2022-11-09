package com.academy.model.repository.interfaces;

import com.academy.model.entity.Users;

/**
 * @author : Volha Salash
 */
public interface UserRepository {
    Users findUserByUsername(String username);

    Users findUserByEmail(String email);

    void addUser(Users user);
}