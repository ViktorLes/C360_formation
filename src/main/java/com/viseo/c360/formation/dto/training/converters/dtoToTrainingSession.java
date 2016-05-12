package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exception.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DtoToTrainingSession implements Converter<TrainingSessionDTO, TrainingSession> {
    @Inject
    TrainingDAO trainingDAO;
    public TrainingSession convert(TrainingSessionDTO dto) {
        TrainingSession domain = new TrainingSession();
        try{
            Training training = trainingDAO.getTraining(dto.getTraining());
            if(training == null) throw new PersistentObjectNotFoundException(dto.getTraining(), "TrainingSession");
            domain.setTraining(training);
        }catch(Exception e) {
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSessionDTO.class),
                    TypeDescriptor.valueOf(TrainingSession.class),
                    dto,
                    new Throwable(e.getMessage())
            );
        }
        return domain;
    }
}
