package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.*;
import com.academy.dto.TicketDto;
import com.academy.model.entity.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TrainMapperImpl implements TrainMapper {

    @Override
    public TrainDto toDto(Trains train) {
        if (train == null) {
            return null;
        }

        TrainDto trainDto = new TrainDto();

        trainDto.setId(train.getId());
        trainDto.setName(train.getName());
        trainDto.setSeats(train.getSeats());
        trainDto.setSchedules(schedulesSetToScheduleDtoSet(train.getSchedules()));
        trainDto.setTickets(ticketsSetToTicketDtoSet(train.getTickets()));
        trainDto.setTrack(tracksToTrackDto(train.getTrack()));

        return trainDto;
    }

    @Override
    public List<TrainDto> toDtoList(List<Trains> trains) {
        if (trains == null) {
            return null;
        }

        List<TrainDto> list = new ArrayList<TrainDto>(trains.size());
        for (Trains trains1 : trains) {
            list.add(toDto(trains1));
        }

        return list;
    }

    @Override
    public Trains toEntity(TrainDto trainDto) {
        if (trainDto == null) {
            return null;
        }

        Trains trains = new Trains();

        trains.setId(trainDto.getId());
        trains.setName(trainDto.getName());
        trains.setSeats(trainDto.getSeats());

        return trains;
    }

    protected Set<ScheduleDto> schedulesSetToScheduleDtoSet(Set<Schedules> set) {
        if (set == null) {
            return null;
        }

        Set<ScheduleDto> set1 = new HashSet<ScheduleDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Schedules schedules : set) {
            set1.add(schedulesToScheduleDto(schedules));
        }

        return set1;
    }

    protected Set<TrainDto> trainsSetToTrainDtoSet(Set<Trains> set) {
        if (set == null) {
            return null;
        }

        Set<TrainDto> set1 = new HashSet<TrainDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Trains trains : set) {
            set1.add(toDto(trains));
        }

        return set1;
    }

    protected Set<MappingDto> mappingsSetToMappingDtoSet(Set<Mappings> set) {
        if (set == null) {
            return null;
        }

        Set<MappingDto> set1 = new HashSet<MappingDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Mappings mappings : set) {
            set1.add(mappingsToMappingDto(mappings));
        }

        return set1;
    }

    protected TrackDto tracksToTrackDto(Tracks tracks) {
        if (tracks == null) {
            return null;
        }

        TrackDto trackDto = new TrackDto();

        trackDto.setId(tracks.getId());
        trackDto.setTrains(trainsSetToTrainDtoSet(tracks.getTrains()));
        trackDto.setMappings(mappingsSetToMappingDtoSet(tracks.getMappings()));

        return trackDto;
    }

    protected MappingDto mappingsToMappingDto(Mappings mappings) {
        if (mappings == null) {
            return null;
        }

        MappingDto mappingDto = new MappingDto();

        mappingDto.setId(mappings.getId());
        mappingDto.setStation(stationsToStationDto(mappings.getStation()));
        mappingDto.setTrack(tracksToTrackDto(mappings.getTrack()));
        mappingDto.setStationOrder(mappings.getStationOrder());

        return mappingDto;
    }

    protected StationDto stationsToStationDto(Stations stations) {
        if (stations == null) {
            return null;
        }

        StationDto stationDto = new StationDto();

        stationDto.setId((stations.getId()));
        stationDto.setName(stations.getName());
        stationDto.setSchedules(schedulesSetToScheduleDtoSet(stations.getSchedules()));
        stationDto.setMappings(mappingsSetToMappingDtoSet(stations.getMappings()));

        return stationDto;
    }

    protected ScheduleDto schedulesToScheduleDto(Schedules schedules) {
        if (schedules == null) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(schedules.getId());
        scheduleDto.setStation(stationsToStationDto(schedules.getStation()));
        scheduleDto.setTrain(toDto(schedules.getTrain()));
        scheduleDto.setTrainStatus(schedules.getTrainStatus());
        if (schedules.getArrivalTime() != null) {
            scheduleDto.setArrivalTime(new SimpleDateFormat().format(schedules.getArrivalTime()));
        }
        if (schedules.getDepartureTime() != null) {
            scheduleDto.setDepartureTime(new SimpleDateFormat().format(schedules.getDepartureTime()));
        }
        scheduleDto.setDirection(schedules.isDirection());

        return scheduleDto;
    }

    protected Set<UserDto> usersSetToUserDtoSet(Set<Users> set) {
        if (set == null) {
            return null;
        }

        Set<UserDto> set1 = new HashSet<UserDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Users users : set) {
            set1.add(usersToUserDto(users));
        }

        return set1;
    }

    protected RoleDto rolesToRoleDto(Roles roles) {
        if (roles == null) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId(roles.getId());
        roleDto.setName(roles.getName());
        roleDto.setUsers(usersSetToUserDtoSet(roles.getUsers()));

        return roleDto;
    }


    protected Set<RoleDto> rolesSetToRoleDtoSet(Set<Roles> set) {
        if (set == null) {
            return null;
        }

        Set<RoleDto> set1 = new HashSet<RoleDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Roles roles : set) {
            set1.add(rolesToRoleDto(roles));
        }

        return set1;
    }


    protected UserDto usersToUserDto(Users users) {
        if (users == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername(users.getUsername());
        userDto.setPassword(users.getPassword());
        userDto.setPasswordConfirm(users.getPasswordConfirm());
     //   userDto.setRoleDto(users.getRoles());

        return userDto;
    }


    protected Set<TicketDto> ticketsSetToTicketDtoSet(Set<Tickets> set) {
        if (set == null) {
            return null;
        }

        Set<TicketDto> set1 = new HashSet<TicketDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Tickets tickets : set) {
            set1.add(ticketsToTicketDto(tickets));
        }

        return set1;
    }

    protected PassengerDto passengersToPassengerDto(Passengers passengers) {
        if (passengers == null) {
            return null;
        }

        PassengerDto passengerDto = new PassengerDto();

        passengerDto.setId(passengers.getId());
        passengerDto.setFirstName(passengers.getFirstName());
        passengerDto.setLastName(passengers.getLastName());
        if (passengers.getBirthDate() != null) {
            passengerDto.setBirthDate(new SimpleDateFormat().format(passengers.getBirthDate()));
        }
        passengerDto.setPassportNumber(passengers.getPassportNumber());
        passengerDto.setUser(usersToUserDto(passengers.getUser()));
        passengerDto.setTickets(ticketsSetToTicketDtoSet(passengers.getTickets()));

        return passengerDto;
    }

    protected TicketDto ticketsToTicketDto(Tickets tickets) {
        if (tickets == null) {
            return null;
        }

        TicketDto ticketDto = new TicketDto();

        ticketDto.setId(tickets.getId());
        ticketDto.setNumber(tickets.getNumber());
        ticketDto.setPassenger(passengersToPassengerDto(tickets.getPassenger()));
        ticketDto.setTrains(trainsSetToTrainDtoSet(tickets.getTrains()));
        ticketDto.setDepartureStation(tickets.getDepartureStation());
        ticketDto.setArrivalStation(tickets.getArrivalStation());
        if (tickets.getDepartureTime() != null) {
            ticketDto.setDepartureTime(new SimpleDateFormat().format(tickets.getDepartureTime()));
        }
        if (tickets.getArrivalTime() != null) {
            ticketDto.setArrivalTime(new SimpleDateFormat().format(tickets.getArrivalTime()));
        }
        if (tickets.getDate() != null) {
            ticketDto.setDate(new SimpleDateFormat().format(tickets.getDate()));
        }
        ticketDto.setPrice(tickets.getPrice());

        return ticketDto;
    }
}
