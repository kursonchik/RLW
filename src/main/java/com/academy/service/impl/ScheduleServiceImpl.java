package com.academy.service.impl;

import com.academy.converterMapper.interfaces.ScheduleMapper;
import com.academy.converterMapper.interfaces.StationMapper;
import com.academy.converterMapper.interfaces.TimetableScheduleMapper;
import com.academy.converterMapper.interfaces.TrainMapper;
import com.academy.dto.*;
import com.academy.exception.TrainsNotFoundException;
import com.academy.model.entity.Schedules;
import com.academy.model.repository.interfaces.ScheduleRepository;
import com.academy.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Volha Salash
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private final AreaService areaService;
    @Autowired
    private final StationService stationService;
    @Autowired
    private final TrackService trackService;
    @Autowired
    private final TrainService trainService;
    @Autowired
    private final MappingService mappingService;
    @Autowired
    private final ScheduleMapper scheduleMapper;
    @Autowired
    private final StationMapper stationMapper;
    @Autowired
    private final TrainMapper trainMapper;
    @Autowired
    private final TimetableScheduleMapper timetableScheduleMapper;

    private static final String ON_SCHEDULE = "On Schedule";
    private static final String DELAYED = "Delayed";
    private static final String CANCELLED = "Cancelled";
    private static final String TIME_WITHOUT_SECONDS = "HH:mm";
    private static final String TIME_WITH_SECONDS = "HH:mm:ss.SSS";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String MIDNIGHT = "00:00";
    private static final String MORNING = "07:00";

    @Override
    public ScheduleDto getSchedule(int id) {
        return scheduleMapper.toDto(scheduleRepository.getSchedule(id));
    }

    @Override
    public List<ScheduleDto> getAllSchedules() {
        return scheduleMapper.toDtoList(scheduleRepository.getAllSchedules());
    }

    @Override
    @Transactional
    public void addSchedule(ScheduleDto scheduleDto) {
        scheduleDto.setTrainStatus(ON_SCHEDULE);
        scheduleRepository.addSchedule(scheduleMapper.toEntity(scheduleDto));
        log.info("Created new schedule for " +
                scheduleDto.getTrain().getName() + " on " + scheduleDto.getStation().getName());
    }

    @Override
    @Transactional
    public void editSchedule(ScheduleDto scheduleDto) {
        scheduleRepository.editSchedule(scheduleMapper.toEntity(scheduleDto));
        log.info("Edited schedule for " +
                scheduleDto.getTrain().getName() + " on " + scheduleDto.getStation().getName());
    }

    @Override
    @Transactional
    public void deleteSchedule(ScheduleDto scheduleDto) {
        scheduleRepository.deleteSchedule(scheduleMapper.toEntity(scheduleDto));
        log.info("Deleted schedule for " +
                scheduleDto.getTrain().getName() + " on " + scheduleDto.getStation().getName());
    }

    @Override
    public void setEndStationForScheduleDto(ScheduleDto scheduleDto) {
        TrackDto trackDto = scheduleDto.getTrain().getTrack();
        int endStationOrder;
        if (scheduleDto.isDirection()) {
            endStationOrder = mappingService.getOrderedStationsByTrack(trackDto).size();
        } else {
            endStationOrder = 1;
        }
        scheduleDto.setEndStation(mappingService.getStationByOrder(trackDto, endStationOrder).getName());
    }

    @Override
    public List<ScheduleDto> getSchedulesByStationAndDirection(StationDto stationDto, boolean direction) {
        List<ScheduleDto> scheduleDtoList =
                scheduleMapper.toDtoList(scheduleRepository.getSchedulesByStationAndDirection(stationMapper.toEntity(stationDto),
                        direction));
        Iterator<ScheduleDto> iterator = scheduleDtoList.iterator();
        while (iterator.hasNext()) {
            ScheduleDto schedule = iterator.next();
            setEndStationForScheduleDto(schedule);
            if (schedule.getDepartureTime() == null && schedule.getArrivalTime() == null) {
                iterator.remove();
            }
        }
        return scheduleDtoList;
    }

    @Override
    public List<ScheduleDto> getSchedulesByRoute(List<StationDto> route) {
        List<ScheduleDto> schedulesByRoute = new ArrayList<>();
        List<AreaDto> areasByRoute = areaService.getAreasByRoute(route);
        for (AreaDto area : areasByRoute) {
            schedulesByRoute.addAll(getSchedulesByStationAndDirection(area.getStationFrom(),
                    area.isDirection()));
        }
        AreaDto lastSection = areasByRoute.get(areasByRoute.size() - 1);
        schedulesByRoute.addAll(getSchedulesByStationAndDirection(lastSection.getStationTo(),
                lastSection.isDirection()));
        return schedulesByRoute;
    }

    @Override
    public List<ScheduleDto> orderSchedulesByTime(List<ScheduleDto> scheduleDtoList) {
        List<Date> arrivalTimes = new ArrayList<>();
        List<ScheduleDto> orderedSchedules = new ArrayList<>();
        List<Date> afterMidnightTimes = new ArrayList<>();
        Date scheduleDepartureTime;

        for (ScheduleDto schedule : scheduleDtoList) {
            scheduleDepartureTime = convertStringtoDate(schedule.getDepartureTime());
            if (scheduleDepartureTime == null) {
                orderedSchedules.add(schedule);
            } else if (scheduleDepartureTime.equals(convertStringtoDate(MIDNIGHT)) ||
                    (scheduleDepartureTime.after(convertStringtoDate(MIDNIGHT)) &&
                            scheduleDepartureTime.before(convertStringtoDate(MORNING)))) {
                afterMidnightTimes.add(scheduleDepartureTime);
            } else {
                arrivalTimes.add(scheduleDepartureTime);
            }
        }

        Collections.sort(arrivalTimes);
        Collections.sort(afterMidnightTimes);
        arrivalTimes.addAll(afterMidnightTimes);

        String trainName = "";
        for (Date time : arrivalTimes) {
            for (ScheduleDto schedule : scheduleDtoList) {
                scheduleDepartureTime = convertStringtoDate(schedule.getDepartureTime());
                if (scheduleDepartureTime != null && scheduleDepartureTime.equals(time) &&
                        !schedule.getTrain().getName().equals(trainName)) {
                    orderedSchedules.add(schedule);
                    trainName = schedule.getTrain().getName();
                    break;
                }
            }
        }
        return orderedSchedules;
    }

    @Override
    public List<ScheduleDto> getSchedulesByTrain(TrainDto trainDto) {
        return scheduleMapper.toDtoList(scheduleRepository.getSchedulesByTrain(trainMapper.toEntity(trainDto)));
    }

    @Override
    public List<ScheduleDto> getSchedulesByStation(StationDto stationDto) {
        List<ScheduleDto> schedules = new ArrayList<>();
        schedules.addAll(getSchedulesByStationAndDirection(stationDto, true));
        schedules.addAll(getSchedulesByStationAndDirection(stationDto, false));
        return orderSchedulesByTime(schedules);
    }

    @Override
    public List<List<ScheduleDto>> getAllSchedulesByStations(List<StationDto> stationDtoList) {
        List<List<ScheduleDto>> schedules = new ArrayList<>();
        for (StationDto station : stationDtoList) {
            schedules.add(getSchedulesByStation(station));
        }
        return schedules;
    }

    @Override
    public Date convertStringtoDate(String date) {
        Date parsedDate = null;
        if (date != null) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(TIME_WITHOUT_SECONDS);
                parsedDate = formatter.parse(date);
            } catch (ParseException pE) {
                pE.getStackTrace();
            }
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                parsedDate = formatter.parse(date);
            } catch (ParseException pE) {
                pE.getStackTrace();
            }
        }
        return parsedDate;
    }

    @Override
    public boolean isTimeBefore(Date d1, Date d2) {
        DateFormat f = new SimpleDateFormat(TIME_WITH_SECONDS);
        return f.format(d1).compareTo(f.format(d2)) < 0;
    }

    @Override
    public List<ScheduleDto> buildSchedule(List<StationDto> route, Date minDepartureTime) {
        List<ScheduleDto> orderedSchedules = orderSchedulesByTime(getSchedulesByRoute(route));
        if (route.size() > 2) {
            setBreakpoints(route);
        }
        List<ScheduleDto> schedules = new ArrayList<>();
        TrackDto currentTrack;
        ScheduleDto currentSchedule = orderedSchedules.get(0);
        Date scheduleArrivalTime;
        Date scheduleDepartureTime;
        mainLoop:
        for (int i = 0; i < route.size(); i++) {
            StationDto currentStation = route.get(i);
            if (i != route.size() - 1) {
                StationDto nextStation = route.get(i + 1);
                currentTrack = areaService.getAreaBetweenStations(currentStation, nextStation).getTrack();
                for (ScheduleDto schedule : orderedSchedules) {
                    scheduleArrivalTime = convertStringtoDate(schedule.getArrivalTime());
                    scheduleDepartureTime = convertStringtoDate(schedule.getDepartureTime());
                    if (currentStation.isBreakpoint()) {
                        if (schedule.getStation().getName().equals(currentStation.getName()) &&
                                schedule.getTrain().getTrack().equals(currentTrack) &&
                                scheduleArrivalTime != null &&
                                !(DateUtils.isSameDay(scheduleDepartureTime, new Date()) &&
                                        schedule.getTrainStatus().equals(CANCELLED)) &&
                                (isTimeBefore(minDepartureTime, scheduleArrivalTime) ||
                                        scheduleArrivalTime.after(convertStringtoDate(MIDNIGHT)) &&
                                                scheduleArrivalTime.before(convertStringtoDate(MORNING)))) {
                            currentSchedule = schedule;
                            minDepartureTime = scheduleArrivalTime;
                            schedules.add(currentSchedule);
                            currentStation.setBreakpoint(false);
                            schedules.addAll(buildSchedule(route.subList(i, route.size()), minDepartureTime));
                            break mainLoop;
                        }
                    } else if (schedule.getStation().getName().equals(currentStation.getName()) &&
                            schedule.getTrain().getTrack().equals(currentTrack) &&
                            scheduleDepartureTime != null &&
                            !(DateUtils.isSameDay(scheduleDepartureTime, new Date()) &&
                                    schedule.getTrainStatus().equals(CANCELLED)) &&
                            (isTimeBefore(minDepartureTime, scheduleDepartureTime) ||
                                    scheduleDepartureTime.after(convertStringtoDate(MIDNIGHT)) &&
                                            scheduleDepartureTime.before(convertStringtoDate(MORNING)))) {
                        currentSchedule = schedule;
                        minDepartureTime = scheduleDepartureTime;
                        schedules.add(currentSchedule);
                        break;
                    }
                }
            } else {
                StationDto previousStation = route.get(i - 1);
                currentTrack = areaService.getAreaBetweenStations(previousStation, currentStation).getTrack();
                for (ScheduleDto schedule : orderedSchedules) {
                    scheduleArrivalTime = convertStringtoDate(schedule.getArrivalTime());
                    if (!schedule.getTrainStatus().equals(CANCELLED) &&
                            schedule.getStation().getName().equals(currentStation.getName()) &&
                            schedule.getTrain().getName().equals(currentSchedule.getTrain().getName()) &&
                            schedule.getTrain().getTrack().equals(currentTrack) &&
                            scheduleArrivalTime != null &&
                            !(DateUtils.isSameDay(scheduleArrivalTime, new Date()) &&
                                    schedule.getTrainStatus().equals(CANCELLED)) &&
                            (isTimeBefore(minDepartureTime, scheduleArrivalTime) ||
                                    scheduleArrivalTime.after(convertStringtoDate(MIDNIGHT)) &&
                                            scheduleArrivalTime.before(convertStringtoDate(MORNING)))) {
                        currentSchedule = schedule;
                        schedules.add(currentSchedule);
                        break;
                    }
                }
            }
        }
        if (schedules.isEmpty()) {
            throw new TrainsNotFoundException("No trains found for route " + route);
        }
        return schedules;
    }

    @Override
    public void setBreakpoints(List<StationDto> route) {
        for (int i = 0; i < route.size() - 2; i++) {
            AreaDto area1 = areaService.getAreaBetweenStations(route.get(i), route.get(i + 1));
            AreaDto area2 = areaService.getAreaBetweenStations(route.get(i + 1), route.get(i + 2));
            if (!area1.getTrack().equals(area2.getTrack())) {
                route.get(i + 1).setBreakpoint(true);
                route.get(i + 1).setEndpoint(false);
            }
        }
    }

    @Override
    @Transactional
    public void createEmptyScheduleForStation(StationDto stationDto, int trackId) {
        TrackDto track = trackService.getTrack(trackId);
        List<TrainDto> trainDtoList = trainService.getTrainsByTrack(track);
        for (TrainDto trainDto : trainDtoList) {
            if (getSchedulesByTrain(trainDto).get(0).isDirection()) {
                scheduleRepository.addSchedule(new Schedules(stationMapper.toEntity(stationDto),
                        trainMapper.toEntity(trainDto), ON_SCHEDULE, true));
            } else {
                scheduleRepository.addSchedule(new Schedules(stationMapper.toEntity(stationDto),
                        trainMapper.toEntity(trainDto), ON_SCHEDULE, false));
            }
        }
    }

    @Override
    @Transactional
    public void delaySchedule(int id, int minutes) {
        ScheduleDto delayOrigin = getSchedule(id);
        List<ScheduleDto> scheduleDtoList = getSchedulesByTrain(delayOrigin.getTrain());
        boolean toBeDelayed = false;
        for (ScheduleDto scheduleDto : scheduleDtoList) {
            if (scheduleDto.equals(delayOrigin)) {
                toBeDelayed = true;
            }
            if (toBeDelayed) {
                SimpleDateFormat sdf = new SimpleDateFormat(TIME_WITHOUT_SECONDS);
                try {
                    if (scheduleDto.getArrivalTime() != null) {
                        scheduleDto.setArrivalTime(sdf.format(DateUtils
                                .addMinutes(sdf.parse(scheduleDto.getArrivalTime()), minutes)));
                    }
                    if (scheduleDto.getDepartureTime() != null) {
                        scheduleDto.setDepartureTime(sdf.format(DateUtils
                                .addMinutes(sdf.parse(scheduleDto.getDepartureTime()), minutes)));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    log.info("Could not parse date from String");
                }
                scheduleDto.setTrainStatus(DELAYED);
                editSchedule(scheduleDto);
            }
        }

    }

    @Override
    @Transactional
    public void cancelSchedule(int id) {
        ScheduleDto cancelOrigin = getSchedule(id);
        List<ScheduleDto> scheduleDtoList = getSchedulesByTrain(cancelOrigin.getTrain());
        boolean toBeCancelled = false;
        for (ScheduleDto scheduleDto : scheduleDtoList) {
            if (scheduleDto.equals(cancelOrigin)) {
                toBeCancelled = true;
            }
            if (toBeCancelled) {
                scheduleDto.setTrainStatus(CANCELLED);
                editSchedule(scheduleDto);
            }
        }
    }

    @Override
    public Map<String, List<TimetableScheduleDto>> getTimetableMap() {
        Map<String, List<TimetableScheduleDto>> timetable = new TreeMap<>();
        List<StationDto> stationDtoList = stationService.getAllStations();
        for (StationDto station : stationDtoList) {
            timetable.put(station.getName(), timetableScheduleMapper.toDtoList(getSchedulesByStation(station)));
        }
        return timetable;
    }
}