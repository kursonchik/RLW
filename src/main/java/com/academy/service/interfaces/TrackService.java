package com.academy.service.interfaces;

import com.academy.dto.TrackDto;

import java.util.List;

/**
 * @author : Volha Salash
 */
public interface TrackService {

    TrackDto getTrack(int id);

    List<TrackDto> getAllTracks();

    void addTrack(TrackDto trackDto);

    void editTrack(TrackDto trackDto);

    void deleteTrack(TrackDto trackDto);
}