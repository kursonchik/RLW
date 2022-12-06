package com.academy.validation;

import com.academy.dto.PassengerDto;
import com.academy.service.interfaces.PassengerService;
import com.academy.service.interfaces.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * @author : Volha Salash
 */
@ExtendWith(MockitoExtension.class)
 class PassengerValidatorTest {

    @InjectMocks
    private PassengerValidator passengerValidator;
    @Mock
    private ScheduleService scheduleService;
    @Mock
    private PassengerService passengerService;

    private static PassengerDto passengerDto;
    private static Errors errors;

    private static final String NAME_VALID = "John";
    private static final String NAME_INVALID = "j0hn!";
    private static final String NAME_LONG = "Johnjohnjohnjohnjohnjohn";
    private final static String DATE_PAST = "18.04.1991";
    private final static String DATE_INVALID = "1.804!199-1";
    private final static int PASSPORT_NUMBER = 12345678;
    private static final String EMPTY_STRING = "";

    @BeforeEach
    public void setUp() throws ParseException {
        passengerDto = PassengerDto.builder()
                .firstName(NAME_VALID)
                .lastName(NAME_VALID)
                .birthDate(DATE_PAST)
                .passportNumber(PASSPORT_NUMBER)
                .build();
        errors = new BeanPropertyBindingResult(passengerDto, "passengerDto");
        lenient().when(scheduleService.convertStringtoDate(anyString()))
                .thenReturn(new SimpleDateFormat("dd.MM.yyyy").parse(DATE_PAST));
        lenient().when(passengerService.getPassengerByPassportNumber(anyInt())).thenReturn(null);
    }

    @Test
     void validatePassengerValid() {
        passengerValidator.validate(passengerDto, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
     void validatePassengerFirstNameEmpty() {
        passengerDto.setFirstName(EMPTY_STRING);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("firstName"));
    }

    @Test
     void validatePassengerFirstNameInvalid() {
        passengerDto.setFirstName(NAME_INVALID);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("firstName"));
    }

    @Test
     void validatePassengerFirstNameLong() {
        passengerDto.setFirstName(NAME_LONG);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("firstName"));
    }

    @Test
     void validatePassengerLastNameEmpty() {
        passengerDto.setLastName(EMPTY_STRING);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("lastName"));
    }

    @Test
     void validatePassengerLastNameInvalid() {
        passengerDto.setLastName(NAME_INVALID);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("lastName"));
    }

    @Test
     void validatePassengerLastNameLong() {
        passengerDto.setLastName(NAME_LONG);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("lastName"));
    }

    @Test
     void validatePassengerBirthDateEmpty() {
        passengerDto.setBirthDate(EMPTY_STRING);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("birthDate"));
    }

    @Test
     void validatePassengerBirthDateInvalid() {
        passengerDto.setBirthDate(DATE_INVALID);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("birthDate"));
    }

    @Test
     void validatePassengerBirthDateNotPast() {
        when(scheduleService.convertStringtoDate(anyString())).thenReturn(new Date());
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("birthDate"));
    }

    @Test
     void validatePassengerPassportNumberEmpty() {
        passengerDto.setPassportNumber(0);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passportNumber"));
    }

    @Test
     void validateStationPassportNumberLong() {
        passengerDto.setPassportNumber(PASSPORT_NUMBER * 10);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passportNumber"));
    }

    @Test
     void validateStationPassportNumberShort() {
        passengerDto.setPassportNumber(PASSPORT_NUMBER / 10);
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passportNumber"));
    }

    @Test
     void validateStationPassportNumberDuplicate() {
        passengerDto.setId(1);
        lenient().when(passengerService.getPassengerByPassportNumber(anyInt()))
                .thenReturn(new PassengerDto());
        passengerValidator.validate(passengerDto, errors);
        assertTrue(errors.hasErrors());
        assertNotNull(errors.getFieldError("passportNumber"));
    }
}