package com.academy.service.interfaces;

import com.academy.dto.StationDto;

import java.util.LinkedList;

/**
 * @author : Volha Salash
 */
public interface PathFinderService {

    void initialize(StationDto source);

    LinkedList<StationDto> createRoute(StationDto target);
}
