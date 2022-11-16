package com.academy.controller;

import com.academy.dto.StationDto;
import com.academy.service.interfaces.ScheduleService;
import com.academy.service.interfaces.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TimetableController {

    private final StationService stationService;
    private final ScheduleService scheduleService;

    @GetMapping(value = "/timetable")
    public ModelAndView timetable() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("timetable");
        List<StationDto> stationDtoList = stationService.getAllStations();
        modelAndView.addObject("stationsList", stationDtoList);
        modelAndView.addObject("schedules", scheduleService.getAllSchedulesByStations(stationDtoList));
        return modelAndView;
    }

    @GetMapping(value = "/timetable/delay/{id}")
    public ModelAndView delaySchedule(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable");
        scheduleService.delaySchedule(id, 15);
        return modelAndView;
    }

    @GetMapping(value = "/timetable/cancel/{id}")
    public ModelAndView cancelSchedule(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/timetable");
        scheduleService.cancelSchedule(id);
        return modelAndView;
    }
}