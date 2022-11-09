package com.academy.model.repository.impl;

import com.academy.model.entity.Passengers;
import com.academy.model.entity.Trains;
import com.academy.model.entity.Users;
import com.academy.model.repository.interfaces.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Repository
public class PassengerRepositoryImpl implements PassengerRepository {

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