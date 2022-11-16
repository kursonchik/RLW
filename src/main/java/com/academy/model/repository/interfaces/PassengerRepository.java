package com.academy.model.repository.interfaces;

import com.academy.model.entity.Passengers;
import com.academy.model.entity.Trains;
import com.academy.model.entity.Users;

import java.util.List;

/**
 * @author : Volha Salash
 */
public interface PassengerRepository {
    Passengers getPassenger(int id);

    List<Passengers> getAllPassengers();

    List<Passengers> getPassengersByTrain(Trains train);

    void addPassenger(Passengers passenger);

    void editPassenger(Passengers passenger);

    void deletePassenger(Passengers passenger);

    Passengers getPassengerByUser(Users user);

    Passengers getPassengerByPassportNumber(int passportNumber);
}
