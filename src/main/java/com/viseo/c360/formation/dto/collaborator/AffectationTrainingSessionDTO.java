package com.viseo.c360.formation.dto.collaborator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AffectationTrainingSessionDTO {
    @NotNull
    List<Long> collaborators;
    public AffectationTrainingSessionDTO() {
    }
    public List<Long> getCollaborators() {
        return collaborators;
    }
    public void setCollaborators(List<Long> collaborators) {
        this.collaborators = collaborators;
    }
}
