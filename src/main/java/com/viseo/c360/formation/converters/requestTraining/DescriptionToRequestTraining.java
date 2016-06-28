package com.viseo.c360.formation.converters.requestTraining;

import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;


public class DescriptionToRequestTraining {

    public DescriptionToRequestTraining() {
    }

    public RequestTraining convert(RequestTrainingDescription dto, Collaborator collaborator, Topic topic) {
        RequestTraining domain = new RequestTraining();
        if (dto.getId() > 0) domain.setId(dto.getId());
        domain.setCollaborator(collaborator);
        domain.setTraining(new DescriptionToTraining().convert(dto.getTrainingDescription(),topic));
        for (TrainingSession trainingSession : new DescriptionToTrainingSession().convert(dto.getTrainingSessionsDescriptions(),topic)) {
            domain.addListSession(trainingSession);
        }
        return domain;
    }
}