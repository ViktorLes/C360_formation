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
    Collaborateur collaborateur;

    @ManyToMany
    List<SessionFormation> listSession;

    public DemandeFormation() {
        super();
        this.listSession = new ArrayList<>();
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
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
