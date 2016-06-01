package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

public class TrainingSessionToDTO{

    public TrainingSessionToDTO () {
    }

    public TrainingSessionDescription convert(TrainingSession source) {

        TrainingSessionDescription dto = new TrainingSessionDescription();
        try {
             if(training == null) throw new PersistentObjectNotFoundException(source.getTraining().getId(), Training.class);
            TrainingDescription trainingDescription = conversionService.convert(training, TrainingDescription.class);
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
            SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
            dto.setId(source.getId());
            dto.setTrainingDescription(trainingDescription);
            dto.setBeginning(formatterDate.format(source.getBeginning()));
            dto.setBeginningTime(formatterTime.format(source.getBeginning()));
            dto.setEnding(formatterDate.format(source.getEnding()));
            dto.setEndingTime(formatterTime.format(source.getEnding()));
            dto.setLocation(source.getLocation());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSession.class),
                    TypeDescriptor.valueOf(TrainingSessionDescription.class),
                    source,
                    new Throwable(e.getMessage())
            );
        }
        return dto;
    }
}
