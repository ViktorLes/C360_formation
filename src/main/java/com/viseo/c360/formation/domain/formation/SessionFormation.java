package com.viseo.c360.formation.domain.formation;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.json.deserializer.formation.SessionFormationDeserializer;

@JsonDeserialize(using = SessionFormationDeserializer.class)
@Entity
public class SessionFormation extends BaseEntity {
	
	@ManyToOne
	Formation formation;

	Date debut;

	Date fin;
	
	String lieu;


	public SessionFormation() {
		super();
	}
	public Formation getFormation() {
		return formation;
	}
	public Date getDebut() {
		return debut;
	}
	public Date getFin() {
		return fin;
	}
	public String getLieu() {
		return lieu;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public void setDebut(Date dateDebut) {
		this.debut = dateDebut;
	}
	public void setFin(Date dateFin) {
		this.fin = dateFin;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

}
