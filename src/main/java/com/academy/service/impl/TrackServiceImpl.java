package com.academy.service.impl;

import com.academy.dto.TrackDto;
import com.academy.mapper.TrackMapper;
import com.academy.model.repository.interfaces.TrackRepository;
import com.academy.service.interfaces.TrackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;

    @Override
    public TrackDto getTrack(int id) {
        return trackMapper.toDto(trackRepository.getTrack(id));
    }

    @Override
    public List<TrackDto> getAllTracks() {
        return trackMapper.toDtoList(trackRepository.getAllTracks());
    }

    @Override
    @Transactional
    public void addTrack(TrackDto trackDto) {
        trackRepository.addTrack(trackMapper.toEntity(trackDto));
        log.info("Created new track " + trackDto.getId());
    }

    @Override
    @Transactional
    public void editTrack(TrackDto trackDto) {
        trackRepository.editTrack(trackMapper.toEntity(trackDto));
        log.info("Edited track " + trackDto.getId());
    }

    @Override
    @Transactional
    public void deleteTrack(TrackDto trackDto) {
        trackRepository.deleteTrack(trackMapper.toEntity(trackDto));
        log.info("Deleted track " + trackDto.getId());
    }
}