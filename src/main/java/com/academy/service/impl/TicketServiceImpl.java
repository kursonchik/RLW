package com.academy.service.impl;

import com.academy.dto.*;
import com.academy.mapper.PassengerMapper;
import com.academy.mapper.TicketMapper;
import com.academy.mapper.TrainMapper;
import com.academy.model.repository.interfaces.TicketRepository;
import com.academy.service.interfaces.AreaService;
import com.academy.service.interfaces.ScheduleService;
import com.academy.service.interfaces.StationService;
import com.academy.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final AreaService areaService;
    private final StationService stationService;
    private final ScheduleService scheduleService;
    private final TicketMapper ticketMapper;
    private final PassengerMapper passengerMapper;
    private final TrainMapper trainMapper;

    private static final int MIN_TIME_TO_DEPARTURE = 600000;

    @Override
    public TicketDto getTicket(int id) {
        return ticketMapper.toDto(ticketRepository.getTicket(id));
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketMapper.toDtoList(ticketRepository.getAllTickets());
    }

    @Override
    @Transactional
    public void addTicket(TicketDto ticketDto) {
        ticketRepository.addTicket(ticketMapper.toEntity(ticketDto));
        log.info("Created new ticket " + ticketDto.getNumber());
    }

    @Override
    @Transactional
    public void editTicket(TicketDto ticketDto) {
        ticketRepository.editTicket(ticketMapper.toEntity(ticketDto));
        log.info("Edited ticket " + ticketDto.getNumber());
    }

    @Override
    @Transactional
    public void deleteTicket(TicketDto ticketDto) {
        ticketRepository.deleteTicket(ticketMapper.toEntity(ticketDto));
        log.info("Deleted ticket " + ticketDto.getNumber());
    }

    @Override
    public long getTicketCount() {
        return ticketRepository.getTicketCount();
    }

    @Override
    public String generateTicketNumber(TicketDto ticketDto) {
        return String.valueOf(ticketDto.getDepartureStation().charAt(0)) +
                ticketDto.getArrivalStation().charAt(0) +
                (ticketDto.getPassenger().getId() + (int) (Math.random() * ((99 - 1) + 1)) + 1) +
                (getTicketCount() + (int) (Math.random() * ((99 - 1) + 1)) + 1);
    }

    @Override
    public double calculateTicketPrice(List<StationDto> route) {
        double ticketPrice = 0;
        List<AreaDto> areas = areaService.getAreasByRoute(route);
        for (AreaDto area : areas) {
            ticketPrice += area.getLength() * 3;
        }
        return ticketPrice;
    }

    @Override
    public List<TicketDto> getTicketsByPassenger(PassengerDto passengerDto) {
        List<TicketDto> ticketDtoList =
                ticketMapper.toDtoList(ticketRepository.getTicketsByPassenger(passengerMapper.toEntity(passengerDto)));
        ticketDtoList.removeIf(this::isExpired);
        return ticketDtoList;
    }

    public boolean isExpired(TicketDto ticketDto) {
        return scheduleService.convertStringtoDate(ticketDto.getDate()).before(new Date());
    }

    @Override
    public List<TicketDto> getTicketsByTrainAndDate(TrainDto trainDto, Date date) {
        return ticketMapper.toDtoList(ticketRepository.getTicketsByTrainAndDate(trainMapper.toEntity(trainDto), date));
    }

    @Override
    public boolean areTicketsAvailable(TicketDto ticketDto) {
        boolean areTicketsAvailable = true;
        Date departureTime = scheduleService.convertStringtoDate(ticketDto.getDepartureTime());
        Date arrivalTime = scheduleService.convertStringtoDate(ticketDto.getArrivalTime());
        Date date = scheduleService.convertStringtoDate(ticketDto.getDate());
        List<AreaDto> areas = areaService.getAreasByRoute
                (stationService.getRoute(ticketDto.getDepartureStation(), ticketDto.getArrivalStation()));
        List<TrainDto> trainDtoList = new ArrayList<>(ticketDto.getTrains());
        for (TrainDto train : trainDtoList) {
            List<TicketDto> existingTickets = getTicketsByTrainAndDate(train, date);
            int existingTicketsCount = 0;
            for (AreaDto area : areas) {
                if (area.getTrack().equals(train.getTrack())) {
                    for (TicketDto ticketItem : existingTickets) {
                        if (scheduleService.convertStringtoDate(ticketItem.getDepartureTime()).before(arrivalTime) &&
                                scheduleService.convertStringtoDate(ticketItem.getArrivalTime()).after(departureTime)) {
                            existingTicketsCount++;
                        }
                    }
                }
                if (existingTicketsCount == train.getSeats()) {
                    areTicketsAvailable = false;
                    break;
                }
            }
        }
        return areTicketsAvailable;
    }

    @Override
    public boolean isPassengerNotPresentOnTrain(TicketDto ticketDto) {
        List<TrainDto> trainDtoList = new ArrayList<>(ticketDto.getTrains());
        Date date = scheduleService.convertStringtoDate(ticketDto.getDate());
        boolean isPassengerNotPresentOnTrain = true;
        for (TrainDto trainDto : trainDtoList) {
            List<TicketDto> existingTickets = getTicketsByTrainAndDate(trainDto, date);
            for (TicketDto ticketItem : existingTickets) {
                if (ticketItem.getPassenger().getFirstName().equals(ticketDto.getPassenger().getFirstName()) &&
                        ticketItem.getPassenger().getLastName().equals(ticketDto.getPassenger().getLastName()) &&
                        ticketItem.getPassenger().getBirthDate().equals(ticketDto.getPassenger().getBirthDate())) {
                    isPassengerNotPresentOnTrain = false;
                    break;
                }
            }
        }
        return isPassengerNotPresentOnTrain;
    }

    @Override
    public boolean isEnoughTimeToDeparture(TicketDto ticketDto) {
        if (!ticketMapper.toEntity(ticketDto).getDate().after(new Date())) {
            LocalTime current = LocalTime.now(ZoneId.of("Europe/Moscow"));
            LocalTime departureTime = scheduleService.convertStringtoDate(ticketDto.getDepartureTime())
                    .toInstant().atZone(ZoneId.of("Europe/Moscow")).toLocalTime();
            return Duration.between(current, departureTime).toMillis() >= MIN_TIME_TO_DEPARTURE;
        } else {
            return true;
        }
    }

    @Override
    public List<String> validateTicket(TicketDto ticketDto) {
        List<String> validationMessages = new ArrayList<>();
        if (!areTicketsAvailable(ticketDto)) {
            validationMessages.add("No more tickets available for this train.");
        }
        if (!isPassengerNotPresentOnTrain(ticketDto)) {
            validationMessages.add("You already have a ticket for this train.");
        }
        if (!isEnoughTimeToDeparture(ticketDto)) {
            validationMessages.add("Less than 10 minutes left until departure.");
        }
        if (areTicketsAvailable(ticketDto) && isPassengerNotPresentOnTrain(ticketDto)
                && isEnoughTimeToDeparture(ticketDto)) {
            validationMessages.add("success");
        }
        return validationMessages;
    }
}