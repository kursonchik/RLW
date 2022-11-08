package com.academy.service;

import com.academy.model.entity.Role;
import com.academy.model.repository.interfaces.RoleRepository;
import com.academy.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author : Volha Salash
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public RoleRepository rolerepository;

    @Override
    public Role findByName(int id) {
        return rolerepository.findRoleByName(id);
    }
}