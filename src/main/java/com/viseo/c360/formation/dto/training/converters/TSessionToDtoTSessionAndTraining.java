package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.BaseTrainingSessionDTO;
import com.viseo.c360.formation.dto.training.TrainingSessionAndTrainingDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

public class TSessionToDtoTSessionAndTraining implements Converter<TrainingSession, TrainingSessionAndTrainingDTO> {

    @Inject
    TrainingDAO trainingDAO;

    public TrainingSessionAndTrainingDTO convert(TrainingSession source) {
        TrainingSessionAndTrainingDTO dto = new TrainingSessionAndTrainingDTO();
        Training training = trainingDAO.getTraining(source.getTraining().getId());
        if(training == null) try {
            throw new PersistentObjectNotFoundException(source.getTraining().getId(), Training.class);
        } catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSession.class),
                    TypeDescriptor.valueOf(TrainingSessionAndTrainingDTO.class),
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
        for(Collaborator collaborator : source.getCollaborators()){
            dto.getCollaborators().add(collaborator.getId());
        }
        return dto;
    }
}
