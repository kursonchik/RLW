package com.academy.services;

import com.academy.converterMapper.interfaces.TicketMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.PassengerDto;
import com.academy.dto.TicketDto;
import com.academy.dto.TrainDto;
import com.academy.model.entity.Tickets;
import com.academy.model.entity.Trains;
import com.academy.model.repository.interfaces.TicketRepository;
import com.academy.service.impl.TicketServiceImpl;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.ScheduleService;
import com.academy.service.interfaces.StationService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author : Volha Salash
 */
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketServiceImpl ticketService;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private AreaService areaService;
    @Mock
    private StationService stationService;
    @Mock
    private TicketMapper ticketMapper;
    @Mock
    private TrainMapper trainMapper;
    @Mock
    private TicketRepository ticketRepository;

    private static final Date DATE_TODAY = new Date();
    private static final Date DATE_TOMORROW = DateUtils.addDays(new Date(), 1);
    private static final Date CURRENT_TIME_PLUS_15_MINUTES = Date.from(Instant.now().plusSeconds(900));
    private static final Date CURRENT_TIME_PLUS_5_MINUTES = Date.from(Instant.now().plusSeconds(300));

    private static TicketDto ticketDto;
    private static Tickets ticketEntity;

    @BeforeAll
    static void setUp() {
        ticketEntity = new Tickets();
        PassengerDto testPassenger = PassengerDto.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .birthDate("01.01.2001")
                .passportNumber(12345678)
                .build();
        TrainDto testTrain = TrainDto.builder()
                .id(1)
                .name("Test I")
                .seats(3)
                .build();
        ticketDto = TicketDto.builder()
                .id(1)
                .number("NUMBER")
                .passenger(testPassenger)
                .trains(new HashSet<>())
                .departureStation("DEPARTURE")
                .arrivalStation("ARRIVAL")
                .departureTime("07:00")
                .arrivalTime("10:00")
                .date("08.11.2022")
                .price(0.0)
                .build();
        ticketDto.getTrains().add(testTrain);
    }

    @Test
    void testGenerateTicketNumber() {
        assertNotNull(ticketService.generateTicketNumber(ticketDto));
    }

    @Test
    void testIsExpiredFalse() {
        when(scheduleService.convertStringtoDate(any())).thenReturn(CURRENT_TIME_PLUS_5_MINUTES);
        assertFalse(ticketService.isExpired(ticketDto));
    }

    @Test
    void testIsExpiredTrue() {
        when(scheduleService.convertStringtoDate(any())).thenReturn(DateUtils.setYears(DATE_TODAY, 2015));
        assertTrue(ticketService.isExpired(ticketDto));
    }

    @Test
    void testAreTicketsAvailable() {
        when(scheduleService.convertStringtoDate(any())).thenReturn(DATE_TODAY);
        when(areaService.getAreasByRoute(anyList())).thenReturn(new ArrayList<>());
        when(scheduleService.convertStringtoDate(anyString())).thenReturn(DATE_TODAY);
        when(stationService.getRoute(anyString(), anyString())).thenReturn(new LinkedList<>());

        assertTrue(ticketService.areTicketsAvailable(ticketDto));
    }

    @Test
    void testIsPassengerNotPresentOnTrain() {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        ticketDtoList.add(ticketDto);

        when(trainMapper.toEntity(any())).thenReturn(new Trains());
        when(ticketRepository.getTicketsByTrainAndDate(any(), any())).thenReturn(new ArrayList<>());
        when(ticketMapper.toDtoList(anyList())).thenReturn(ticketDtoList);

        assertFalse(ticketService.isPassengerNotPresentOnTrain(ticketDto));
    }

    @Test
    void testIsEnoughTimeToDepartureOnAnotherDate() {
        ticketEntity.setDate(DATE_TOMORROW);

        when(ticketMapper.toEntity(ticketDto)).thenReturn(ticketEntity);

        assertTrue(ticketService.isEnoughTimeToDeparture(ticketDto));
    }

    @Test
    void testIsEnoughTimeToDepartureTodayWithFifteenMinutesLeft() {
        ticketEntity.setDate(DATE_TODAY);

        when(ticketMapper.toEntity(ticketDto)).thenReturn(ticketEntity);
        when(scheduleService.convertStringtoDate(any())).thenReturn(CURRENT_TIME_PLUS_15_MINUTES);

        assertTrue(ticketService.isEnoughTimeToDeparture(ticketDto));
    }

    @Test
    void testIsEnoughTimeToDepartureTodayWithFiveMinutesLeft() {
        ticketEntity.setDate(DATE_TODAY);

        when(ticketMapper.toEntity(ticketDto)).thenReturn(ticketEntity);
        when(scheduleService.convertStringtoDate(any())).thenReturn(CURRENT_TIME_PLUS_5_MINUTES);

        assertFalse(ticketService.isEnoughTimeToDeparture(ticketDto));
    }
}