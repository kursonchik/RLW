package com.academy.model.repository.impl;

import com.academy.model.entity.Mappings;
import com.academy.model.entity.Stations;
import com.academy.model.entity.Tracks;
import com.academy.model.repository.interfaces.MappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author : Volha Salash
 */

@Repository
public class MappingRepositoryImpl implements MappingRepository {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Mappings getMapping(int id) {
        return entityManager.find(Mappings.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Mappings> getAllMappings() {
        Query query = entityManager.createQuery("SELECT m FROM Mappings m");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Mappings> getMappingsByTrack(Tracks track) {
        Query query = entityManager.createQuery("SELECT m FROM Mappings m " +
                "WHERE m.track = :track ORDER BY m.stationOrder");
        query.setParameter("track", track);
        return query.getResultList();
    }

    @Override
    public void addMapping(Mappings mapping) {
        entityManager.persist(entityManager.merge(mapping));
    }

    @Override
    public void editMapping(Mappings mapping) {
        entityManager.merge(mapping);
    }

    @Override
    public void deleteMapping(Mappings mapping) {
        entityManager.remove(entityManager.merge(mapping));
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Stations> getOrderedStationsByTrack(Tracks track) {
        Query query = entityManager.createQuery("SELECT m.station FROM Mappings m " +
                "WHERE m.track = :track ORDER BY m.stationOrder");
        query.setParameter("track", track);
        return query.getResultList();
    }

    @Override
    public Tracks getTrack(int id) {
        Query query = entityManager.createQuery("SELECT m.track FROM Mappings m WHERE m.track.id = :id");
        query.setParameter("id", id);
        return (Tracks) query.getSingleResult();
    }

    @Override
    public int getStationOrder(Stations station, Tracks track) {
        Query query = entityManager.createQuery("SELECT m.stationOrder FROM Mappings m " +
                "WHERE m.station = :station AND m.track = :track");
        query.setParameter("station", station);
        query.setParameter("track", track);
        return (int) query.getSingleResult();
    }

    @Override
    public Stations getStationByOrder(Tracks track, int stationOrder) {
        Query query = entityManager.createQuery("SELECT m.station FROM Mappings m " +
                "WHERE m.track = :track AND m.stationOrder = :stationOrder");
        query.setParameter("stationOrder", stationOrder);
        query.setParameter("track", track);
        return (Stations) query.getSingleResult();
    }
}
