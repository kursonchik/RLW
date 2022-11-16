package com.academy.model.repository.interfaces;

import com.academy.model.entity.Areas;
import com.academy.model.entity.Stations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface AreaRepository {
    Areas getArea(int id);

    List<Areas> getAllAreas();

    void addArea(Areas section);

    void editArea(Areas section);

    void deleteArea(Areas section);

    Areas getAreaBetweenStations(Stations stationFrom, Stations stationTo);

    List<Areas> getAreasByRoute(List<Stations> route);
}