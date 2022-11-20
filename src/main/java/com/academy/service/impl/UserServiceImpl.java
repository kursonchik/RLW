package com.academy.service.impl;

import com.academy.dto.UserDto;
import com.academy.mapper.UserMapper;
import com.academy.model.entity.Users;
import com.academy.model.repository.interfaces.RoleRepository;
import com.academy.model.repository.interfaces.UserRepository;
import com.academy.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void save(UserDto userDto) {
        Users user = userMapper.toEntity(userDto);
        user.setPassword(user.getPassword());
        user.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")));
        userRepository.addUser(user);
        log.info("Created new user " + user.getUsername());
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return userMapper.toDto(userRepository.findUserByUsername(username));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userMapper.toDto(userRepository.findUserByEmail(email));
    }


    @Override
    public String getCurrentUserName() {
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }

}