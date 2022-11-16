package com.academy.model.repository.interfaces;

import com.academy.model.entity.Schedules;
import com.academy.model.entity.Stations;
import com.academy.model.entity.Trains;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Volha Salash
 */

public interface ScheduleRepository {
    Schedules getSchedule(int id);

    List<Schedules> getAllSchedules();

    void addSchedule(Schedules schedule);

    void editSchedule(Schedules schedule);

    void deleteSchedule(Schedules schedule);

    List<Schedules> getSchedulesByStationAndDirection(Stations station, boolean direction);

    List<Schedules> getSchedulesByTrain(Trains train);
}
