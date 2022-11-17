package com.academy.service.impl;

import com.academy.dto.MappingDto;
import com.academy.dto.StationDto;
import com.academy.dto.TrackDto;
import com.academy.mapper.MappingMapper;
import com.academy.mapper.StationMapper;
import com.academy.mapper.TrackMapper;
import com.academy.model.entity.Mappings;
import com.academy.model.repository.impl.MappingRepositoryImpl;
import com.academy.model.repository.interfaces.MappingRepository;
import com.academy.service.interfaces.MappingService;
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
@RequiredArgsConstructor
public class MappingServiceImpl implements MappingService {
    @Autowired
    private final MappingRepository mappingRepository;
    private final TrackService trackService;
    private final MappingMapper mappingMapper;
    private final TrackMapper trackMapper;
    private final StationMapper stationMapper;

    @Override
    public MappingDto getMapping(int id) {
        return mappingMapper.toDto(mappingRepository.getMapping(id));
    }

    @Override
    public List<MappingDto> getAllMappings() {
        return mappingMapper.toDtoList(mappingRepository.getAllMappings());
    }

    @Override
    public List<MappingDto> getMappingsByTrack(TrackDto trackDto) {
        return mappingMapper.toDtoList(mappingRepository.getMappingsByTrack(trackMapper.toEntity(trackDto)));
    }

    @Override
    @Transactional
    public void addMapping(MappingDto mappingDto) {
        mappingRepository.addMapping(mappingMapper.toEntity(mappingDto));
        log.info("Created new mapping " + mappingDto.getId());
    }

    @Override
    @Transactional
    public void editMapping(MappingDto mappingDto) {
        mappingRepository.editMapping(mappingMapper.toEntity(mappingDto));
        log.info("Edited mapping " + mappingDto.getId());
    }

    @Override
    @Transactional
    public void deleteMapping(MappingDto mappingDto) {
        List<MappingDto> mappingDtoList = getMappingsByTrack(mappingDto.getTrack());
        for (int i = mappingDto.getStationOrder(); i < mappingDtoList.size(); i++) {
            mappingDtoList.get(i).setStationOrder(i);
            mappingRepository.editMapping(mappingMapper.toEntity(mappingDtoList.get(i)));
        }
        mappingRepository.deleteMapping(mappingMapper.toEntity(mappingDto));
        log.info("Deleted mapping " + mappingDto.getId());
    }

    @Override
    public List<StationDto> getOrderedStationsByTrack(TrackDto trackDto) {
        return stationMapper.toDtoList(mappingRepository.getOrderedStationsByTrack(trackMapper.toEntity(trackDto)));
    }

    @Override
    public TrackDto getTrack(int id) {
        return trackMapper.toDto(mappingRepository.getTrack(id));
    }

    @Override
    @Transactional
    public void appendStation(StationDto stationDto, int trackId, String appendLocation) {
        TrackDto trackDto = trackService.getTrack(trackId);
        int stationOrder;
        switch (appendLocation) {
            case "before":
                stationOrder = 1;
                List<MappingDto> mappingDtoList = getMappingsByTrack(trackDto);
                if (mappingDtoList.get(0).getStationOrder() == 1) {
                    for (MappingDto mappingDto : mappingDtoList) {
                        mappingDto.setStationOrder(mappingDto.getStationOrder() + 1);
                        mappingRepository.editMapping(mappingMapper.toEntity(mappingDto));
                    }
                }
                mappingRepository.addMapping(new Mappings(stationMapper.toEntity(stationDto),
                        trackMapper.toEntity(trackDto), stationOrder));
                break;
            case "after":
                stationOrder = getOrderedStationsByTrack(trackDto).size() + 1;
                mappingRepository.addMapping(new Mappings(stationMapper.toEntity(stationDto),
                        trackMapper.toEntity(trackDto), stationOrder));
                break;
        }
    }

    @Override
    public int getStationOrder(StationDto stationDto, TrackDto trackDto) {
        return mappingRepository.getStationOrder(stationMapper.toEntity(stationDto), trackMapper.toEntity(trackDto));
    }

    @Override
    public StationDto getStationByOrder(TrackDto trackDto, int stationOrder) {
        return stationMapper.toDto(mappingRepository.getStationByOrder(trackMapper.toEntity(trackDto), stationOrder));
    }
}
