package com.academy.model.repository.impl;

import com.academy.model.entity.Schedules;
import com.academy.model.entity.Stations;
import com.academy.model.entity.Trains;
import com.academy.model.repository.interfaces.ScheduleRepository;
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
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Schedules getSchedule(int id) {
        return entityManager.find(Schedules.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Schedules> getAllSchedules() {
        Query query = entityManager.createQuery("SELECT s FROM Schedules s");
        return query.getResultList();
    }

    @Override
    public void addSchedule(Schedules schedule) {
        entityManager.persist(entityManager.merge(schedule));
    }

    @Override
    public void editSchedule(Schedules schedule) {
        entityManager.merge(schedule);
    }

    @Override
    public void deleteSchedule(Schedules schedule) {
        entityManager.remove(entityManager.merge(schedule));
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Schedules> getSchedulesByStationAndDirection(Stations station, boolean direction) {
        Query query = entityManager.createQuery("SELECT s FROM Schedules s " +
                "WHERE s.station.name = :stationName AND s.direction = :direction");
        query.setParameter("stationName", station.getName());
        query.setParameter("direction", direction);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Schedules> getSchedulesByTrain(Trains train) {
        Query query = entityManager.createQuery("SELECT s FROM Schedules s WHERE s.train = :train");
        query.setParameter("train", train);
        return query.getResultList();
    }
}