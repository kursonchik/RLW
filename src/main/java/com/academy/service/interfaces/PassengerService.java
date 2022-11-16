package com.academy.service.interfaces;

import com.academy.dto.PassengerDto;
import com.academy.dto.UserDto;

import java.util.List;

/**
 * @author : Volha Salash
 */
public interface PassengerService {

    PassengerDto getPassenger(int id);

    List<PassengerDto> getAllPassengers();

    PassengerDto getPassengerByUser(UserDto userDto);

    PassengerDto getPassengerByPassportNumber(int passportNumber);

    void addPassenger(PassengerDto passengerDto);

    void editPassenger(PassengerDto passengerDto);

    void deletePassenger(PassengerDto passengerDto);
}