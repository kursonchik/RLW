package com.academy.model.repository.impl;

import com.academy.model.entity.Stations;
import com.academy.model.entity.Trains;
import com.academy.model.repository.interfaces.StationRepository;
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
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stations getStation(int id) {
        return entityManager.find(Stations.class, id);
    }

    @Override
    public Stations getStationByName(String name) {
        Stations station;
        try {
            station = (Stations) entityManager.createQuery("SELECT s FROM Stations s " +
                    "WHERE s.name = :name").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            station = null;
        }
        return station;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Stations> getAllStations() {
        Query query = entityManager.createQuery("SELECT s FROM Stations s ORDER BY s.name");
        return query.getResultList();
    }

    @Override
    public void addStation(Stations station) {
        entityManager.persist(station);
    }

    @Override
    public void editStation(Stations station) {
        entityManager.merge(station);
    }

    @Override
    public void deleteStation(Stations station) {
        entityManager.remove(entityManager.merge(station));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Stations> getStationsByTrain(Trains train) {
        Query query = entityManager.createQuery("SELECT DISTINCT s.station FROM Schedules s WHERE s.train = :train");
        query.setParameter("train", train);
        return query.getResultList();
    }
}
