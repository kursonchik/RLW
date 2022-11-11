package com.academy.service.impl;

import com.academy.dto.PassengerDto;
import com.academy.dto.ScheduleDto;
import com.academy.dto.TrackDto;
import com.academy.dto.TrainDto;
import com.academy.mapper.PassengerMapper;
import com.academy.mapper.TrackMapper;
import com.academy.mapper.TrainMapper;
import com.academy.model.repository.interfaces.PassengerRepository;
import com.academy.model.repository.interfaces.TrainRepository;
import com.academy.service.interfaces.MessagingService;
import com.academy.service.interfaces.TrainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final TrainMapper trainMapper;
    private final TrackMapper trackMapper;
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final MessagingService messagingService;

    @Override
    public TrainDto getTrain(int id) {
        return trainMapper.toDto(trainRepository.getTrain(id));
    }

    @Override
    public TrainDto getTrainByName(String name) {
        return trainMapper.toDto(trainRepository.getTrainByName(name));
    }

    @Override
    public List<TrainDto> getAllTrains() {
        return trainMapper.toDtoList(trainRepository.getAllTrains());
    }

    @Override
    @Transactional
    public void addTrain(TrainDto trainDto) {
        trainRepository.addTrain(trainMapper.toEntity(trainDto));
        log.info("Created new train " + trainDto.getName());
    }

    @Override
    @Transactional
    public void editTrain(TrainDto trainDto) {
        trainRepository.editTrain(trainMapper.toEntity(trainDto));
        log.info("Updated train " + trainDto.getName());
    }

    @Override
    public void deleteTrain(TrainDto trainDto) {

    }
/*
    @Override
    @Transactional
    public void deleteTrain(TrainDto trainDto) {
        if (!getPassengersByTrainId(trainDto.getId()).isEmpty()) {
            throw new IllegalOperationException("Attempted to delete a train with passengers.");
        }
        trainRepository.deleteTrain(trainMapper.toEntity(trainDto));
        log.info("Deleted train " + trainDto.getName());
        messagingService.sendMessage();
    }

 */

    @Override
    public Set<TrainDto> getTrainsBySchedule(List<ScheduleDto> scheduleDtoList) {
        Set<TrainDto> trainDtoSet = new HashSet<>();
        TrainDto comparedTrain = scheduleDtoList.get(0).getTrain();
        trainDtoSet.add(comparedTrain);
        for (int i = 1; i < scheduleDtoList.size(); i++) {
            if (!scheduleDtoList.get(i).getTrain().equals(comparedTrain)) {
                trainDtoSet.add(scheduleDtoList.get(i).getTrain());
                comparedTrain = scheduleDtoList.get(i).getTrain();
            }
        }
        return trainDtoSet;
    }

    @Override
    public List<TrainDto> getTrainsByTrack(TrackDto trackDto) {
        return trainMapper.toDtoList(trainRepository.getTrainsByTrack(trackMapper.toEntity(trackDto)));
    }

    @Override
    public List<PassengerDto> getPassengersByTrainId(int trainId) {
        return passengerMapper.toDtoList(passengerRepository.getPassengersByTrain(trainMapper.toEntity(getTrain(trainId))));
    }

    @Override
    public TrainDto updateTrainDto(TrainDto trainDto) {
        return getTrainByName(trainDto.getName());
    }
}