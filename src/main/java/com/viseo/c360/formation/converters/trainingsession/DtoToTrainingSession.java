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

public class DtoToTrainingSession implements Converter<TrainingSessionDTO, TrainingSession> {

    @Inject
    TrainingDAO trainingDAO;

    public TrainingSession convert(TrainingSessionDTO dto) {
        TrainingSession domain = new TrainingSession();
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
        try{
            if(dto.getId() > 0) domain.setId(dto.getId());
            Training training = trainingDAO.getTraining(dto.getTraining().getId());
            if(training == null) throw new PersistentObjectNotFoundException(dto.getTraining().getId(), TrainingSession.class);
            domain.setTraining(training);
            domain.setBeginning(formatterDate.parse(dto.getBeginning()+"|"+dto.getBeginningTime()));
            domain.setEnding(formatterDate.parse(dto.getEnding()+"|"+dto.getEndingTime()));
            domain.setLocation(dto.getLocation());
        }catch(Exception e) {
            e.printStackTrace();
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
