package com.academy.model.repository.impl;

import com.academy.model.entity.Passengers;
import com.academy.model.entity.Trains;
import com.academy.model.entity.Users;
import com.academy.model.repository.interfaces.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author : Volha Salash
 */
@Repository
public class PassengerRepositoryImpl implements PassengerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Passengers> getAllPassengers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Passengers> criteriaQuery = criteriaBuilder.createQuery(Passengers.class);
        Root<Passengers> root = criteriaQuery.from(Passengers.class);

        criteriaQuery
                .select(root);
        TypedQuery<Passengers> selectAll = entityManager.createQuery(criteriaQuery);

        return selectAll.getResultList();
    }

    @Override
    public Passengers getPassenger(int id) {
        return entityManager.find(Passengers.class, id);
    }

    @Override
    public List<Passengers> getPassengersByTrain(Trains train) {
        Query query = entityManager.createQuery("SELECT DISTINCT t.passenger FROM Tickets t " +
                "WHERE :train member of t.trains AND t.date <= CURRENT_DATE");
        query.setParameter("train", train);
        return query.getResultList();
    }

    @Override
    public void addPassenger(Passengers passenger) {
        entityManager.persist(entityManager.merge(passenger));
    }

    @Override
    public void editPassenger(Passengers passenger) {
        entityManager.merge(passenger);
    }

    @Override
    public void deletePassenger(Passengers passenger) {
        entityManager.remove(entityManager.merge(passenger));
    }

    @Override
    public Passengers getPassengerByUser(Users user) {
        Passengers passenger = null;
        try {
            Query query = entityManager.createQuery("SELECT p FROM Passengers p where p.user = :user");
            query.setParameter("user", user);
            passenger = (Passengers) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Passengers getPassengerByPassportNumber(int passportNumber) {
        Passengers passenger = null;
        try {
            Query query = entityManager.createQuery("SELECT p FROM Passengers p where p.passportNumber = :passportNumber");
            query.setParameter("passportNumber", passportNumber);
            passenger = (Passengers) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return passenger;
    }

}

    /*
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public Passengers getPassenger(int id) {
        return entityManager.find(Passengers.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passengers> getAllPassengers() {
        Query query = entityManager.createQuery("SELECT p FROM Passengers p", Passengers.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passengers> getPassengersByTrain(Trains train) {
        Query query = entityManager.createQuery("SELECT DISTINCT t.passenger FROM Tickets t " +
                "WHERE :train member of t.trains AND t.date <= CURRENT_DATE");
        query.setParameter("train", train);
        return query.getResultList();
    }

    @Override
    public void addPassenger(Passengers passenger) {
        entityManager.persist(entityManager.merge(passenger));
    }

    @Override
    public void editPassenger(Passengers passenger) {
        entityManager.merge(passenger);
    }

    @Override
    public void deletePassenger(Passengers passenger) {
        entityManager.remove(entityManager.merge(passenger));
    }

    @Override
    public Passengers getPassengerByUser(Users user) {
        Passengers passenger = null;
        try {
            Query query = entityManager.createQuery("SELECT p FROM Passengers p where p.user = :user");
            query.setParameter("user", user);
            passenger = (Passengers) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Passengers getPassengerByPassportNumber(int passportNumber) {
        Passengers passenger = null;
        try {
            Query query = entityManager.createQuery("SELECT p FROM Passengers p where p.passportNumber = :passportNumber");
            query.setParameter("passportNumber", passportNumber);
            passenger = (Passengers) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return passenger;
    }

  }

 */