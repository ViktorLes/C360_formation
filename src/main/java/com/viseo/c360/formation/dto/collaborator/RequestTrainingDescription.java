package com.viseo.c360.formation.dto.collaborator;

import java.util.List;

import com.viseo.c360.formation.dto.BaseDTO;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;


public class RequestTrainingDescription extends BaseDTO {


    TrainingDescription trainingDescription;

    List<TrainingSessionDescription> trainingSessionDescriptions;

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

    public List<TrainingSessionDescription> getTrainingSessionDescriptions() {
        return trainingSessionDescriptions;
    }

    public void setTrainingSessionDescriptions(List<TrainingSessionDescription> trainingSessionDescriptions) {
        this.trainingSessionDescriptions = trainingSessionDescriptions;
    }

    public CollaboratorIdentity getCollaboratorIdentity() {
        return collaboratorIdentity;
    }

    public void setCollaboratorIdentity(CollaboratorIdentity collaboratorIdentity) {
        this.collaboratorIdentity = collaboratorIdentity;
    }
}
