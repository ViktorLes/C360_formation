package com.viseo.c360.formation.domain.collaborator;

import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.formation.domain.training.TrainingSession;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*** useless**/
@Entity
public class AffectationTrainingSession extends BaseEntity {
    @NotNull
    @Valid
    @ManyToMany
    List<Collaborator> collaborators;
    @NotNull
    @Valid
    TrainingSession trainingSession;
    public AffectationTrainingSession() {
        this.collaborators = new ArrayList<>();
    }
    public List<Collaborator> getCollaborators() {
        return Collections.unmodifiableList(collaborators);
    }
    public void addCollaborator(Collaborator collaborator) {
        this.collaborators.add(collaborator);
    }
    public void removeCollaborator(Collaborator collaborator) {
        this.collaborators.remove(collaborator);
    }
    public TrainingSession getTrainingSession() {
        return trainingSession;
    }
    public void setTrainingSession(TrainingSession trainingSession) {
        this.trainingSession = trainingSession;
    }
}
