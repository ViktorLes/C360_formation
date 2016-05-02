package com.viseo.c360.formation.domain.collaborateur;

import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;

import javax.persistence.*;
import java.util.List;


@Entity
public class DemandeFormation {
    @Id
    @GeneratedValue
    long id;

    @Version
    long version;

    @ManyToOne
    Formation formation;

    @ManyToOne
    Collaborateur collaborateur;

    @ManyToMany
    List<SessionFormation> listSession;

    public DemandeFormation() {super();}

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SessionFormation> getListSession() {
        return listSession;
    }

    public void setListSession(List<SessionFormation> listSession) {
        this.listSession = listSession;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
