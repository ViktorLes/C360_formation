package com.viseo.c360.formation.converters.requestTraining;


import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

import javax.inject.Inject;

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
                    TypeDescriptor.valueOf(RequestTraining.class),
                    dto,
                    new Throwable(e.getMessage())
            );
        }
        return domain;
    }
}