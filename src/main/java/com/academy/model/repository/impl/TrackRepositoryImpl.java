package com.academy.model.repository.impl;

import com.academy.model.entity.Tracks;
import com.academy.model.repository.interfaces.TrackRepository;
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
public class TrackRepositoryImpl implements TrackRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Tracks getTrack(int id) {
        return entityManager.find(Tracks.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Tracks> getAllTracks() {
        Query query = entityManager.createQuery("SELECT t FROM Tracks t");
        return query.getResultList();
    }

    @Override
    public void addTrack(Tracks track) {
        entityManager.persist(track);
    }

    @Override
    public void editTrack(Tracks track) {
        entityManager.merge(track);
    }

    @Override
    public void deleteTrack(Tracks track) {
        entityManager.remove(entityManager.merge(track));
    }
}