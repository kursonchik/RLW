package com.academy.service.interfaces;

import com.academy.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Volha Salash
 */
public interface UserService {

    void save(UserDto userDto);

    UserDto findUserByUsername(String username);

    UserDto findUserByEmail(String email);

    String getCurrentUserName();
}