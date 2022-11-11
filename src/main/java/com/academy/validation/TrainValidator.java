package com.academy.validation;

import com.academy.dto.TrainDto;
import com.academy.service.interfaces.TrainService;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainValidator implements Validator {

    private final TrainService trainService;

    @Override
    public boolean supports(Class<?> aClass) {
        return TrainDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        TrainDto train = (TrainDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "track.id", "NotEmpty");

        if (!String.valueOf(train.getTrack().getId()).matches("\\d")) {
            errors.rejectValue("track.id", "Format.trainForm.track.id");
        }
        if (train.getTrack().getId() < 1 || train.getTrack().getId() > 5) {
            errors.rejectValue("track.id", "Range.trainForm.track.id");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        if (!train.getName().matches("^[a-zA-Z]+ [MDCLXVI]+$")) {
            errors.rejectValue("name", "Format.trainForm.name");
        }
        if (train.getName().length() > 20) {
            errors.rejectValue("name", "Size.trainForm.name");
        }

        if (trainService.getTrainByName(train.getName()) != null &&
                trainService.getTrainByName(train.getName()).getId() != train.getId()) {
            errors.rejectValue("name", "Duplicate.trainForm.name");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "NotEmpty");

        if (!String.valueOf(train.getCapacity()).matches("\\d+")) {
            errors.rejectValue("capacity", "Format.trainForm.capacity");
        }
        if (train.getCapacity() < 10 || train.getCapacity() > 50) {
            errors.rejectValue("capacity", "Range.trainForm.capacity");
        }
    }
}