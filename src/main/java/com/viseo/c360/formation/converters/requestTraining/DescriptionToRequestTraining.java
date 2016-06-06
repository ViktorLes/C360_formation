package com.viseo.c360.formation.converters.requestTraining;

import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.converters.trainingsession.ListDescriptionToListTrainingSession;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

public class DescriptionToRequestTraining {

    public DescriptionToRequestTraining() {
    }

    public RequestTraining convert(RequestTrainingDescription dto, Collaborator collaborator) {
        RequestTraining domain = new RequestTraining();
        try {
            if (dto.getId() > 0) domain.setId(dto.getId());
            domain.setCollaborator(collaborator);
            domain.setTraining(new DescriptionToTraining().convert(dto.getTrainingDescription()));
            for (TrainingSession trainingSession : new ListDescriptionToListTrainingSession().convert(dto.getTrainingSessionsDescriptions())) {
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