package com.viseo.c360.formation.converters.requestTraining;


<<<<<<< HEAD
=======

>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
<<<<<<< HEAD
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
=======
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDTO;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
<<<<<<< HEAD

=======
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
import java.util.List;

import javax.inject.Inject;

<<<<<<< HEAD
public class DtoToRequestTraining {

    public DtoToRequestTraining() {
    }

    public RequestTraining convert(RequestTrainingDescription dto) {
        RequestTraining domain = new RequestTraining();

        try {
            if (dto.getId() > 0) domain.setId(dto.getId());

            domain.setCollaborator(collaborator);
            domain.setTraining(training);
            for (TrainingSession trainingSession : listTrainingSession) {
                domain.addListSession(trainingSession);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(RequestTrainingDescription.class),
=======
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
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
                    TypeDescriptor.valueOf(RequestTraining.class),
                    dto,
                    new Throwable(e.getMessage())
            );
        }
        return domain;
    }
}