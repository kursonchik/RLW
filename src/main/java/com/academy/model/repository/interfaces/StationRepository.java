package com.academy.model.repository.interfaces;

import com.academy.model.entity.Stations;
import com.academy.model.entity.Trains;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface StationRepository {
    Stations getStation(int id);

    Stations getStationByName(String username);

    List<Stations> getAllStations();

    void addStation(Stations station);

    void editStation(Stations station);

    void deleteStation(Stations station);

    List<Stations> getStationsByTrain(Trains train);
}
