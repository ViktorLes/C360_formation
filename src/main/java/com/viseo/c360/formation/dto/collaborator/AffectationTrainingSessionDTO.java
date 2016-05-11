package com.viseo.c360.formation.dto.collaborator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AffectationTrainingSessionDTO {
    @NotNull
    @Min(value = 1)
    long trainingSession;
    @NotNull
    List<Long> collaborators;
    public AffectationTrainingSessionDTO() {
    }
    public long getTrainingSession() {
        return trainingSession;
    }
    public List<Long> getCollaborators() {
        return collaborators;
    }
    public void setCollaborators(List<Long> collaborators) {
        this.collaborators = collaborators;
    }
    public void setTrainingSession(long trainingSession) {
        this.trainingSession = trainingSession;
    }
}
