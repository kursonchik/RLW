package com.academy.model.repository.interfaces;

import com.academy.model.entity.Tracks;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TrackRepository {
    Tracks getTrack(int id);

    List<Tracks> getAllTracks();

    void addTrack(Tracks track);

    void editTrack(Tracks track);

    void deleteTrack(Tracks track);
}
