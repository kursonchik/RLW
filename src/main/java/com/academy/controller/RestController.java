package com.academy.controller;

import com.academy.mapper.TimetableScheduleMapper;
import com.academy.service.interfaces.ScheduleService;
import com.academy.service.interfaces.StationService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : Volha Salash
 */
@Log4j2
@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestController {

    private final ScheduleService scheduleService;
    private final StationService stationService;
    private final TimetableScheduleMapper timetableScheduleMapper;

    @GetMapping(value = "/timetable/{stationName}")
    public String generateResponse(@PathVariable String stationName) {
        log.info("Received request from 2nd app");
        return new Gson().toJson(timetableScheduleMapper.toDtoList(
                scheduleService.getSchedulesByStation(stationService.getStationByName(stationName))));
    }

    @GetMapping(value = "/timetable/all")
    public String generateResponse() {
        log.info("Received request from 2nd app");
        return new Gson().toJson(scheduleService.getTimetableMap());
    }
}