package com.viseo.c360.formation.domain.collaborateur;

import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Entity
public class DemandeFormation extends BaseEntity {

    @ManyToOne
    Formation formation;

    @ManyToOne
    Collaborator collaborator;

    @ManyToMany
    List<SessionFormation> listSession;

    public DemandeFormation() {
        super();
        this.listSession = new ArrayList<>();
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public List<SessionFormation> getListSession() {
        return Collections.unmodifiableList(listSession);
    }

    public void addListSession(SessionFormation sessionFormation) {
        this.listSession.add(sessionFormation);
    }

    public void removeListSession(SessionFormation sessionFormation) {
        this.listSession.remove(sessionFormation);
    }
}
