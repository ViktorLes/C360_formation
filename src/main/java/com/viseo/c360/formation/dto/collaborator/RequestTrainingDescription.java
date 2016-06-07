package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import com.viseo.c360.formation.dto.BaseDTO;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;


public class RequestTrainingDescription extends BaseDTO {


    TrainingDescription trainingDescription;

    List<TrainingSessionDescription> trainingSessionsDescription;

    CollaboratorIdentity collaboratorIdentity;

    public RequestTrainingDescription() {
        super();
    }

    public TrainingDescription getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(TrainingDescription trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    public List<TrainingSessionDescription> getTrainingSessionsDescription() {
        return trainingSessionsDescription;
    }

    public void setTrainingSessionsDescription(List<TrainingSessionDescription> trainingSessionsDescription) {
        this.trainingSessionsDescription = trainingSessionsDescription;
    }

    public CollaboratorIdentity getCollaboratorIdentity() {
        return collaboratorIdentity;
    }

    public void setCollaboratorIdentity(CollaboratorIdentity collaboratorIdentity) {
        this.collaboratorIdentity = collaboratorIdentity;
    }
}
