package com.academy.model.repository.interfaces;

import com.academy.model.entity.Tracks;
import com.academy.model.entity.Trains;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface TrainRepository {
    Trains getTrain(int id);

    Trains getTrainByName(String name);

    List<Trains> getAllTrains();

    void addTrain(Trains train);

    void editTrain(Trains train);

    void deleteTrain(Trains train);

    List<Trains> getTrainsByTrack(Tracks track);
}