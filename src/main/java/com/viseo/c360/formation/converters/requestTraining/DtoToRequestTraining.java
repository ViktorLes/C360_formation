package com.viseo.c360.formation.converters.requestTraining;



import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDTO;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import java.util.List;

import javax.inject.Inject;

public class DtoToRequestTraining implements Converter<RequestTrainingDTO, RequestTraining> {

    @Inject
    TrainingDAO trainingDAO;
    CollaboratorDAO collaboratorDAO;
    ConversionService conversionService;

    public DtoToRequestTraining (ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public RequestTraining convert(RequestTrainingDTO dto) {
        RequestTraining domain = new RequestTraining();

        try{
            if(dto.getId() > 0) domain.setId(dto.getId());
            Collaborator collaborator = collaboratorDAO.getCollaborator(dto.getCollaboratorDTO().getId());
            Training training = trainingDAO.getTraining(dto.getTrainingDTO().getId());
            List<TrainingSession> listTrainingSession = conversionService.convert(dto.getTrainingSessionsDtos(),List.class);
                if(training == null)
                    throw new PersistentObjectNotFoundException(dto.getTrainingDTO().getId(), TrainingSessionDTO.class);
                if(collaborator == null)
                    throw new PersistentObjectNotFoundException(dto.getCollaboratorDTO().getId(), CollaboratorDTO.class);

            domain.setCollaborator(collaborator);
            domain.setTraining(training);
            for(TrainingSession trainingSession: listTrainingSession) {
                domain.addListSession(trainingSession);
            }
        }catch(Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(RequestTrainingDTO.class),
                    TypeDescriptor.valueOf(RequestTraining.class),
                    dto,
                    new Throwable(e.getMessage())
            );
        }
        return domain;
    }
}