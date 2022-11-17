package com.academy.validation;

import com.academy.dto.StationDto;
import com.academy.service.interfaces.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author : Volha Salash
 */
@Component
@RequiredArgsConstructor
public class StationValidator implements Validator {
    @Autowired
    private final StationService stationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return StationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        StationDto station = (StationDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        if (!station.getName().matches("^[a-zA-Z]+")) {
            errors.rejectValue("name", "Format.stationForm.name");
        }
        if (station.getName().length() > 20) {
            errors.rejectValue("name", "Size.stationForm.name");
        }

        if (stationService.getStationByName(station.getName()) != null &&
                stationService.getStationByName(station.getName()).getId() != station.getId()) {
            errors.rejectValue("name", "Duplicate.stationForm.name");
        }
    }
}