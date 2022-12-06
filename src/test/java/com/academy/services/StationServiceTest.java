package com.academy.services;

import com.academy.dto.StationDto;
import com.academy.exception.IllegalOperationException;
import com.academy.service.impl.StationServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author : Volha Salash
 */
@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    @InjectMocks
    private StationServiceImpl stationService;
    @Disabled
    @Test
    void testDeleteStationException() {
        StationDto stationDto = new StationDto();
        stationDto.setId(18);

        assertThrows(IllegalOperationException.class, () -> stationService.editStation(stationDto));
    }

}