package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;

public class SessionDTOToSession implements Converter<TrainingSessionDTO, TrainingSession> {
    @Inject
    TrainingDAO trainingDAO;
    @Inject
    CollaboratorDAO collaboratorDAO;

    public TrainingSession convert(TrainingSessionDTO dto) {
        TrainingSession domain = new TrainingSession();
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
        try{
            Training training = trainingDAO.getTraining(dto.getTrainingId());
            if(training == null) throw new PersistentObjectNotFoundException(dto.getTrainingId(), TrainingSession.class);
            domain.setTraining(training);
            domain.setBeginning(formatterDate.parse(dto.getBeginning()+"|"+dto.getBeginningTime()));
            domain.setEnding(formatterDate.parse(dto.getEnding()+"|"+dto.getEndingTime()));
            domain.setLocation(dto.getLocation());
            for(long i : dto.getCollaborators()){
                Collaborator collaborator = collaboratorDAO.getCollaborator(i);
                if(collaborator == null) throw new PersistentObjectNotFoundException(i, Collaborator.class);
                domain.addCollaborator(collaborator);
            }
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
