package com.academy.model.repository.interfaces;

import com.academy.model.entity.Mappings;
import com.academy.model.entity.Stations;
import com.academy.model.entity.Tracks;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface MappingRepository {
    Mappings getMapping(int id);

    List<Mappings> getAllMappings();

    List<Mappings> getMappingsByTrack(Tracks track);

    void addMapping(Mappings mapping);

    void editMapping(Mappings mapping);

    void deleteMapping(Mappings mapping);

    List<Stations> getOrderedStationsByTrack(Tracks track);

    Tracks getTrack(int id);

    int getStationOrder(Stations station, Tracks track);

    Stations getStationByOrder(Tracks track, int stationOrder);
}
