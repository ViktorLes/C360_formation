package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
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
        }catch(){
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSessionDTO.class),
                    TypeDescriptor.valueOf(TrainingSession.class),
                    dto,
                    new Throwable("no TrainingSession with id : '"+Long.toString(dto.getTraining())+"' has been found.")
            );
        }
        domain.setTraining(training);
        return domain;
    }
}
