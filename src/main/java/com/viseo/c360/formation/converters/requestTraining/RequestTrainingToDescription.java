package com.viseo.c360.formation.converters.requestTraining;

import com.viseo.c360.formation.converters.training.TrainingToDescription;
import com.viseo.c360.formation.converters.trainingsession.TrainingSessionToDescription;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.dto.collaborator.CollaboratorIdentity;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;

public class RequestTrainingToDescription {

    public RequestTrainingDescription convert(RequestTraining source){
        RequestTrainingDescription dto = new RequestTrainingDescription();
        CollaboratorIdentity collaboratorIdentity = new CollaboratorIdentity();
        collaboratorIdentity.setId(source.getCollaborator().getId());
        collaboratorIdentity.setVersion(source.getCollaborator().getVersion());
        dto.setId(source.getId());
        dto.setVersion(source.getVersion());
        dto.setCollaboratorIdentity(collaboratorIdentity);
        dto.setTrainingDescription(new TrainingToDescription().convert(source.getTraining()));
        dto.setTrainingSessionsDescriptions(new TrainingSessionToDescription().convert(source.getListSession()));
        return dto;
    }
}
