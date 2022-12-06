package com.academy.model.repository.impl;

import com.academy.model.entity.Tracks;
import com.academy.model.entity.Trains;
import com.academy.model.repository.interfaces.TrainRepository;
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
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Trains getTrain(int id) {
        return entityManager.find(Trains.class, id);
    }

    @Override
    public Trains getTrainByName(String name) {
        Trains train;
        try {
            train = (Trains) entityManager.createQuery("SELECT t FROM Trains t " +
                    "WHERE t.name = :name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            train = null;
        }
        return train;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Trains> getAllTrains() {
        Query query = entityManager.createQuery("SELECT t FROM Trains t ORDER BY t.track.id, t.name");
        return query.getResultList();
    }

    @Override
    public void addTrain(Trains train) {
        entityManager.persist(entityManager.merge(train));
    }

    @Override
    public void editTrain(Trains train) {
        entityManager.merge(train);
    }

    @Override
    public void deleteTrain(Trains train) {
        entityManager.remove(entityManager.merge(train));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Trains> getTrainsByTrack(Tracks track) {
        Query query = entityManager.createQuery("SELECT t FROM Trains t WHERE t.track = :track");
        query.setParameter("track)", track);
        return query.getResultList();
    }
}
