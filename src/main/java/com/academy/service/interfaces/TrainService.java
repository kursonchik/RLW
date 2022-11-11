package com.academy.service.interfaces;

import com.academy.dto.PassengerDto;
import com.academy.dto.ScheduleDto;
import com.academy.dto.TrackDto;
import com.academy.dto.TrainDto;

import java.util.List;
import java.util.Set;

/**
 * @author : Volha Salash
 */
public interface TrainService {

    TrainDto getTrain(int id);

    TrainDto getTrainByName(String name);

    List<TrainDto> getAllTrains();

    void addTrain(TrainDto trainDto);

    void editTrain(TrainDto trainDto);

    void deleteTrain(TrainDto trainDto);

    Set<TrainDto> getTrainsBySchedule(List<ScheduleDto> scheduleDtoList);

    List<TrainDto> getTrainsByTrack(TrackDto trackDto);

    List<PassengerDto> getPassengersByTrainId(int trainId);

    TrainDto updateTrainDto(TrainDto trainDto);
}