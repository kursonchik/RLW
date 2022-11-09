package com.academy.model.repository.interfaces;

import com.academy.model.entity.Roots;
import com.academy.model.entity.Trains;

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

    List<Trains> getTrainsByRoot(Roots root);
}