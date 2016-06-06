package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DescriptionToTrainingSession {

    public TrainingSession convert(TrainingSessionDescription dto, Training training) {
        TrainingSession domain = new TrainingSession();
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
        try {
            if (dto.getId() > 0) domain.setId(dto.getId());
            domain.setTraining(training);
            domain.setBeginning(formatterDate.parse(dto.getBeginning() + "|" + dto.getBeginningTime()));
            domain.setEnding(formatterDate.parse(dto.getEnding() + "|" + dto.getEndingTime()));
            domain.setLocation(dto.getLocation());
        } catch (ParseException e) {
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSessionDescription.class),
                    TypeDescriptor.valueOf(TrainingSession.class),
                    dto,
                    new Throwable(e.getMessage()));
        }
        return domain;
    }
}
