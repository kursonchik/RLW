package com.academy.service.impl;

import com.academy.dto.AreaDto;
import com.academy.dto.StationDto;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.PathFinderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class PathFinderServiceImpl implements PathFinderService {

    private final AreaService areaService;
    private List<AreaDto> areas;
    private Set<StationDto> settledStations;
    private Set<StationDto> unsettledStations;
    private Map<StationDto, StationDto> predecessors;
    private Map<StationDto, Double> distance;

    @Override
    public void initialize(StationDto source) {
        areas = areaService.getAllAreas();
        settledStations = new HashSet<>();
        unsettledStations = new HashSet<>();
        predecessors = new HashMap<>();
        distance = new HashMap<>();
        distance.put(source, 0.0);
        unsettledStations.add(source);
        while (unsettledStations.size() > 0) {
            StationDto station = getMinimum(unsettledStations);
            settledStations.add(station);
            unsettledStations.remove(station);
            findMinimumDistances(station);
        }
    }

    private void findMinimumDistances(StationDto station) {
        List<StationDto> adjacentStations = getNeighbors(station);
        for (StationDto target : adjacentStations) {
            if (getShortestDistance(target) > getShortestDistance(station)
                    + getDistance(station, target)) {
                distance.put(target, getShortestDistance(station)
                        + getDistance(station, target));
                predecessors.put(target, station);
                unsettledStations.add(target);
            }
        }
    }

    private double getDistance(StationDto station, StationDto target) {
        for (AreaDto area : areas) {
            if (area.getStationFrom().equals(station)
                    && area.getStationTo().equals(target)) {
                return area.getDistance();
            }
        }
        throw new RuntimeException("Something went wrong");
    }

    private List<StationDto> getNeighbors(StationDto station) {
        List<StationDto> neighbors = new ArrayList<>();
        for (AreaDto area : areas) {
            if (area.getStationFrom().equals(station)
                    && !isSettled(area.getStationTo())) {
                neighbors.add(area.getStationTo());
            }
        }
        return neighbors;
    }

    private StationDto getMinimum(Set<StationDto> stations) {
        StationDto minimum = null;
        for (StationDto station : stations) {
            if (minimum == null) {
                minimum = station;
            } else {
                if (getShortestDistance(station) < getShortestDistance(minimum)) {
                    minimum = station;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(StationDto station) {
        return settledStations.contains(station);
    }

    private double getShortestDistance(StationDto destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    @Override
    public LinkedList<StationDto> createRoute(StationDto target) {
        LinkedList<StationDto> path = new LinkedList<>();
        StationDto step = target;
        if (predecessors.get(step) == null) {
            log.error("Route not found");
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}