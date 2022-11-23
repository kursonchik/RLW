package com.academy.service.impl;

import com.academy.converterMapper.interfaces.PassengerMapper;
import com.academy.converterMapper.interfaces.UserMapper;
import com.academy.dto.PassengerDto;
import com.academy.dto.UserDto;
import com.academy.model.repository.interfaces.PassengerRepository;
import com.academy.service.interfaces.PassengerService;
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
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;
    private final UserMapper userMapper;


    @Override
    public List<PassengerDto> getAllPassengers() {
        return passengerMapper.toDtoList(passengerRepository.getAllPassengers());
    }

    @Override
    public PassengerDto getPassenger(int id) {
        return passengerMapper.toDto(passengerRepository.getPassenger(id));
    }

    @Override
    public PassengerDto getPassengerByUser(UserDto userDto) {
        return passengerMapper.toDto(passengerRepository.getPassengerByUser(userMapper.toEntity(userDto)));
    }

    @Override
    public PassengerDto getPassengerByPassportNumber(int passportNumber) {
        return passengerMapper.toDto(passengerRepository.getPassengerByPassportNumber(passportNumber));
    }

    @Override
    @Transactional
    public void addPassenger(PassengerDto passengerDto) {
        passengerRepository.addPassenger(passengerMapper.toEntity(passengerDto));
        log.info("Created new passenger " + passengerDto.getFirstName() + " " + passengerDto.getLastName());
    }

    @Override
    @Transactional
    public void editPassenger(PassengerDto passengerDto) {
        passengerRepository.editPassenger(passengerMapper.toEntity(passengerDto));
        log.info("Edited passenger " + passengerDto.getFirstName() + " " + passengerDto.getLastName());
    }

    @Override
    @Transactional
    public void deletePassenger(PassengerDto passengerDto) {
        passengerRepository.deletePassenger(passengerMapper.toEntity(passengerDto));
        log.info("Deleted passenger " + passengerDto.getFirstName() + " " + passengerDto.getLastName());
    }

}
