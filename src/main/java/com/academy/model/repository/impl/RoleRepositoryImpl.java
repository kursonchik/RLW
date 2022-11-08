package com.academy.model.repository.impl;

import com.academy.model.entity.Role;
import com.academy.model.repository.interfaces.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author : Volha Salash
 */
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(int id) {
        Role role;
        try {
            role = (Role) entityManager.createQuery("SELECT t FROM Role t where t.name = :roleName")
                    .setParameter("name", id).getSingleResult();
        } catch (NoResultException e) {
            role = null;
        }
        return role;
    }
}