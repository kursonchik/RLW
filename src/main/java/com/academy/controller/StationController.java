package com.academy.controller;

import com.academy.dto.StationDto;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.MappingService;
import com.academy.service.interfaces.ScheduleService;
import com.academy.service.interfaces.StationService;
import com.academy.validation.StationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author : Volha Salash
 */
@Controller
// @SessionAttributes("station")
@RequiredArgsConstructor
public class StationController {
    @Autowired
    private final StationService stationService;
    @Autowired
    private final MappingService mappingService;
    @Autowired
    private final AreaService areaService;
    @Autowired
    private final ScheduleService scheduleService;
    @Autowired
    private final StationValidator stationValidator;

    @GetMapping(value = "/stations")
    public ModelAndView allStations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stations");
        modelAndView.addObject("stationsList", stationService.getAllStations());
        return modelAndView;
    }

    @GetMapping(value = "/stations/edit/{id}")
    public ModelAndView editStation(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stationEdit");
        modelAndView.addObject("station", stationService.getStation(id));
        return modelAndView;
    }

    @PostMapping(value = "/stations/edit")
    public ModelAndView editStation(@ModelAttribute("station") StationDto stationDto,
                                    BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        stationValidator.validate(stationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("stationEdit");
            stationDto.setName("");
        } else {
            modelAndView.setViewName("redirect:/stations");
            stationService.editStation(stationDto);
        }
        return modelAndView;
    }

    @GetMapping(value = "/stations/add")
    public ModelAndView addStation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stationEdit");
        modelAndView.addObject("station", new StationDto());
        return modelAndView;
    }

    @PostMapping(value = "/stations/add")
    public ModelAndView addStation(@ModelAttribute("station") StationDto stationDto,
                                   @RequestParam Map<String, String> requestParams,
                                   RedirectAttributes redirectAttributes,
                                   BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        stationValidator.validate(stationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("stationEdit");
            stationDto.setName("");
        } else {
            modelAndView.setViewName("redirect:/stations/edit/" + stationDto.getId() + "/track");
            redirectAttributes.addAttribute("track", requestParams.get("track"));
            redirectAttributes.addAttribute("distance", requestParams.get("distance"));
        }
        return modelAndView;
    }

    @GetMapping(value = "/stations/edit/{id}/track")
    public ModelAndView editTrack(@ModelAttribute("station") StationDto stationDto,
                                  @RequestParam Map<String, String> requestParams) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stationMapping");
        int trackNumber = Integer.parseInt(requestParams.get("track"));
        modelAndView.addObject("stationsList",
                mappingService.getOrderedStationsByTrack(mappingService.getTrack(trackNumber)));
        modelAndView.addObject("track", requestParams.get("track"));
        modelAndView.addObject("distance", requestParams.get("distance"));
        return modelAndView;
    }

    @PostMapping(value = "/stations/edit/track")
    public ModelAndView editTrack(@RequestParam Map<String, String> requestParams,
                                  @ModelAttribute("station") StationDto stationDto) {
        ModelAndView modelAndView = new ModelAndView();
        int trackNumber = Integer.parseInt(requestParams.get("track"));
        mappingService.appendStation(stationDto, trackNumber, requestParams.get("appendLocation"));
        stationDto = stationService.updateStationDto(stationDto);
        areaService.createArea(stationDto, Integer.parseInt(requestParams.get("length")),
                mappingService.getTrack(trackNumber));
        scheduleService.createEmptyScheduleForStation(stationDto, trackNumber);
        modelAndView.setViewName("redirect:/stations");
        return modelAndView;
    }

    @GetMapping(value = "/stations/delete/{id}")
    public ModelAndView deleteStation(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/stations");
        stationService.deleteStation(stationService.getStation(id));
        return modelAndView;
    }
}