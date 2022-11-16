package com.academy.model.repository.impl;

import com.academy.model.entity.Areas;
import com.academy.model.entity.Stations;
import com.academy.model.repository.interfaces.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Repository
public class AreaRepositoryImpl implements AreaRepository {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Areas getArea(int id) {
        return entityManager.find(Areas.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Areas> getAllAreas() {
        Query query = entityManager.createQuery("SELECT s FROM Areas s");
        return query.getResultList();
    }

    @Override
    public void addArea(Areas area) {
        entityManager.persist(entityManager.merge(area));
    }

    @Override
    public void editArea(Areas area) {
        entityManager.merge(area);
    }

    @Override
    public void deleteArea(Areas area) {
        entityManager.remove(entityManager.merge(area));
    }

    @Override
    public Areas getAreaBetweenStations(Stations stationFrom, Stations stationTo) {
        Query query = entityManager.createQuery("SELECT s FROM SectionEntity s " +
                "WHERE s.stationFrom = :stationFrom AND s.stationTo = :stationTo");
        query.setParameter("stationFrom", stationFrom);
        query.setParameter("stationTo", stationTo);
        return (Areas) query.getSingleResult();
    }

    @Override
    public List<Areas> getAreasByRoute(List<Stations> route) {
        List<Areas> sectionsByRoute = new ArrayList<>();
        for (int i = 0; i < route.size() - 1; i++) {
            sectionsByRoute.add(getAreaBetweenStations(route.get(i), route.get(i + 1)));
        }
        return sectionsByRoute;
    }
}