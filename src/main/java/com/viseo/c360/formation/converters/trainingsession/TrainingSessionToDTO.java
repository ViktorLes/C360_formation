package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

public class TrainingSessionToDTO implements Converter<TrainingSession, TrainingSessionDTO> {

    @Inject
    TrainingDAO trainingDAO;

    public TrainingSessionDTO convert(TrainingSession source) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        Training training = trainingDAO.getTraining(source.getTraining().getId());
        if(training == null) try {
            throw new PersistentObjectNotFoundException(source.getTraining().getId(), Training.class);
        } catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSession.class),
                    TypeDescriptor.valueOf(TrainingSessionDTO.class),
                    source,
                    new Throwable(e.getMessage())
            );
        }
        dto.setTraining(training);
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        dto.setBeginning(formatterDate.format(source.getBeginning()));
        dto.setBeginningTime(formatterTime.format(source.getBeginning()));
        dto.setEnding(formatterDate.format(source.getEnding()));
        dto.setEndingTime(formatterTime.format(source.getEnding()));
        dto.setLocation(source.getLocation());
        return dto;
    }


}
