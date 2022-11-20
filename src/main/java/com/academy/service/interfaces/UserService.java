package com.academy.service.interfaces;

import com.academy.dto.UserDto;

/**
 * @author : Volha Salash
 */
public interface UserService {

    void save(UserDto userDto);

    UserDto findUserByUsername(String username);

    UserDto findUserByEmail(String email);

    String getCurrentUserName();
}