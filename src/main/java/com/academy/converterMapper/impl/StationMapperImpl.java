package com.academy.converterMapper.impl;

import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.dto.*;
import com.academy.model.entity.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class StationMapperImpl implements StationMapper {

    @Override
    public StationDto toDto(Stations station) {
        if (station == null) {
            return null;
        }

        StationDto stationDto = new StationDto();

        stationDto.setId(station.getId());
        stationDto.setName(station.getName());
        stationDto.setSchedules(schedulesSetToScheduleDtoSet(station.getSchedules()));
        stationDto.setMappings(mappingsSetToMappingDtoSet(station.getMappings()));

        return stationDto;
    }

    @Override
    public List<StationDto> toDtoList(List<Stations> stations) {
        if (stations == null) {
            return null;
        }

        List<StationDto> list = new ArrayList<StationDto>(stations.size());
        for (Stations stations1 : stations) {
            list.add(toDto(stations1));
        }

        return list;
    }

    @Override
    public Stations toEntity(StationDto trainDto) {
        if (trainDto == null) {
            return null;
        }

        Stations stations = new Stations();

        stations.setId(trainDto.getId());
        stations.setName(trainDto.getName());
        stations.setEndpoint(trainDto.isEndpoint());
        stations.setBreakpoint(trainDto.isBreakpoint());
        stations.setSchedules(scheduleDtoSetToSchedulesSet(trainDto.getSchedules()));
        stations.setMappings(mappingDtoSetToMappingsSet(trainDto.getMappings()));

        return stations;
    }

    @Override
    public List<Stations> toEntityList(List<StationDto> stationDtoList) {
        if (stationDtoList == null) {
            return null;
        }

        List<Stations> list = new ArrayList<Stations>(stationDtoList.size());
        for (StationDto stationDto : stationDtoList) {
            list.add(toEntity(stationDto));
        }

        return list;
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
        userDto.setRoleDto(rolesSetToRoleDtoSet(users.getRoles()));

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

    protected Set<TrainDto> trainsSetToTrainDtoSet(Set<Trains> set) {
        if (set == null) {
            return null;
        }

        Set<TrainDto> set1 = new HashSet<TrainDto>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (Trains trains : set) {
            set1.add(trainsToTrainDto(trains));
        }

        return set1;
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

    protected MappingDto mappingsToMappingDto(Mappings mappings) {
        if (mappings == null) {
            return null;
        }

        MappingDto mappingDto = new MappingDto();

        mappingDto.setId(mappings.getId());
        mappingDto.setStation(toDto(mappings.getStation()));
        mappingDto.setTrack(tracksToTrackDto(mappings.getTrack()));
        mappingDto.setStationOrder(mappings.getStationOrder());

        return mappingDto;
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

    protected TrainDto trainsToTrainDto(Trains trains) {
        if (trains == null) {
            return null;
        }

        TrainDto trainDto = new TrainDto();

        trainDto.setId(trains.getId());
        trainDto.setName(trains.getName());
        trainDto.setSeats(trains.getSeats());
        trainDto.setSchedules(schedulesSetToScheduleDtoSet(trains.getSchedules()));
        trainDto.setTickets(ticketsSetToTicketDtoSet(trains.getTickets()));
        trainDto.setTrack(tracksToTrackDto(trains.getTrack()));

        return trainDto;
    }

    protected ScheduleDto schedulesToScheduleDto(Schedules schedules) {
        if (schedules == null) {
            return null;
        }

        ScheduleDto scheduleDto = new ScheduleDto();

        scheduleDto.setId(schedules.getId());
        scheduleDto.setStation(toDto(schedules.getStation()));
        scheduleDto.setTrain(trainsToTrainDto(schedules.getTrain()));
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

    protected Set<Schedules> scheduleDtoSetToSchedulesSet(Set<ScheduleDto> set) {
        if (set == null) {
            return null;
        }

        Set<Schedules> set1 = new HashSet<Schedules>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (ScheduleDto scheduleDto : set) {
            try {
                set1.add(scheduleDtoToSchedules(scheduleDto));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return set1;
    }

    protected Set<Users> userDtoSetToUsersSet(Set<UserDto> set) {
        if (set == null) {
            return null;
        }

        Set<Users> set1 = new HashSet<Users>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (UserDto userDto : set) {
            set1.add(userDtoToUsers(userDto));
        }

        return set1;
    }

    protected Roles roleDtoToRoles(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }

        Roles roles = new Roles();

        roles.setUsers(userDtoSetToUsersSet(roleDto.getUsers()));
        roles.setId(roleDto.getId());
        roles.setName(roleDto.getName());

        return roles;
    }

    protected Set<Roles> roleDtoSetToRolesSet(Set<RoleDto> set) {
        if (set == null) {
            return null;
        }

        Set<Roles> set1 = new HashSet<Roles>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (RoleDto roleDto : set) {
            set1.add(roleDtoToRoles(roleDto));
        }

        return set1;
    }

    protected Users userDtoToUsers(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        Users users = new Users();

        users.setPassword(userDto.getPassword());
        users.setPasswordConfirm(userDto.getPasswordConfirm());
        users.setRoles(roleDtoSetToRolesSet(userDto.getRoleDto()));

        return users;
    }

    protected Set<Tickets> ticketDtoSetToTicketsSet(Set<TicketDto> set) {
        if (set == null) {
            return null;
        }

        Set<Tickets> set1 = new HashSet<Tickets>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TicketDto ticketDto : set) {
            try {
                set1.add(ticketDtoToTickets(ticketDto));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        return set1;
    }

    protected Passengers passengerDtoToPassengers(PassengerDto passengerDto) throws ParseException {
        if (passengerDto == null) {
            return null;
        }

        Passengers passengers = new Passengers();

        passengers.setId(passengerDto.getId());
        passengers.setFirstName(passengerDto.getFirstName());
        passengers.setLastName(passengerDto.getLastName());
        if (passengerDto.getBirthDate() != null) {
            passengers.setBirthDate(new SimpleDateFormat().parse(passengerDto.getBirthDate()));
        }
        passengers.setPassportNumber(passengerDto.getPassportNumber());
        passengers.setUser(userDtoToUsers(passengerDto.getUser()));
        passengers.setTickets(ticketDtoSetToTicketsSet(passengerDto.getTickets()));

        return passengers;
    }

    protected Set<Trains> trainDtoSetToTrainsSet(Set<TrainDto> set) {
        if (set == null) {
            return null;
        }

        Set<Trains> set1 = new HashSet<Trains>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (TrainDto trainDto : set) {
            set1.add(trainDtoToTrains(trainDto));
        }

        return set1;
    }

    protected Tickets ticketDtoToTickets(TicketDto ticketDto) throws ParseException {
        if (ticketDto == null) {
            return null;
        }

        Tickets tickets = new Tickets();

        tickets.setId(ticketDto.getId());
        tickets.setNumber(ticketDto.getNumber());
        tickets.setPassenger(passengerDtoToPassengers(ticketDto.getPassenger()));
        tickets.setTrains(trainDtoSetToTrainsSet(ticketDto.getTrains()));
        tickets.setDepartureStation(ticketDto.getDepartureStation());
        tickets.setArrivalStation(ticketDto.getArrivalStation());
        if (ticketDto.getDepartureTime() != null) {
            tickets.setDepartureTime(new SimpleDateFormat().parse(ticketDto.getDepartureTime()));
        }
        if (ticketDto.getArrivalTime() != null) {
            tickets.setArrivalTime(new SimpleDateFormat().parse(ticketDto.getArrivalTime()));
        }
        if (ticketDto.getDate() != null) {
            tickets.setDate(new SimpleDateFormat().parse(ticketDto.getDate()));
        }
        tickets.setPrice(ticketDto.getPrice());

        return tickets;
    }

    protected Mappings mappingDtoToMappings(MappingDto mappingDto) {
        if (mappingDto == null) {
            return null;
        }

        Mappings mappings = new Mappings();

        mappings.setId(mappingDto.getId());
        mappings.setStation(toEntity(mappingDto.getStation()));
        mappings.setTrack(trackDtoToTracks(mappingDto.getTrack()));
        mappings.setStationOrder(mappingDto.getStationOrder());

        return mappings;
    }

    protected Set<Mappings> mappingDtoSetToMappingsSet(Set<MappingDto> set) {
        if (set == null) {
            return null;
        }

        Set<Mappings> set1 = new HashSet<Mappings>(Math.max((int) (set.size() / .75f) + 1, 16));
        for (MappingDto mappingDto : set) {
            set1.add(mappingDtoToMappings(mappingDto));
        }

        return set1;
    }

    protected Tracks trackDtoToTracks(TrackDto trackDto) {
        if (trackDto == null) {
            return null;
        }

        Tracks tracks = new Tracks();

        tracks.setId(trackDto.getId());
        tracks.setTrains(trainDtoSetToTrainsSet(trackDto.getTrains()));
        tracks.setMappings(mappingDtoSetToMappingsSet(trackDto.getMappings()));

        return tracks;
    }

    protected Trains trainDtoToTrains(TrainDto trainDto) {
        if (trainDto == null) {
            return null;
        }

        Trains trains = new Trains();

        trains.setId(trainDto.getId());
        trains.setName(trainDto.getName());
        trains.setSeats(trainDto.getSeats());
        trains.setSchedules(scheduleDtoSetToSchedulesSet(trainDto.getSchedules()));
        trains.setTickets(ticketDtoSetToTicketsSet(trainDto.getTickets()));
        trains.setTrack(trackDtoToTracks(trainDto.getTrack()));

        return trains;
    }

    protected Schedules scheduleDtoToSchedules(ScheduleDto scheduleDto) throws ParseException {
        if (scheduleDto == null) {
            return null;
        }

        Schedules schedules = new Schedules();

        schedules.setId(scheduleDto.getId());
        schedules.setStation(toEntity(scheduleDto.getStation()));
        schedules.setTrainStatus(scheduleDto.getTrainStatus());
        schedules.setTrain(trainDtoToTrains(scheduleDto.getTrain()));
        if (scheduleDto.getArrivalTime() != null) {
            schedules.setArrivalTime(new SimpleDateFormat().parse(scheduleDto.getArrivalTime()));
        }
        if (scheduleDto.getDepartureTime() != null) {
            schedules.setDepartureTime(new SimpleDateFormat().parse(scheduleDto.getDepartureTime()));
        }
        schedules.setDirection(scheduleDto.isDirection());

        return schedules;
    }
}
