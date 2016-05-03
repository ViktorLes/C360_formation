package com.viseo.c360.formation.domain.formation;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.json.deserializer.formation.SessionFormationDeserializer;

@JsonDeserialize(using = SessionFormationDeserializer.class)
@Entity
public class SessionFormation extends BaseEntity {
	
	@ManyToOne
	Training training;

	Date debut;

	Date fin;
	
	String lieu;


	public SessionFormation() {
		super();
	}
	public Training getTraining() {
		return training;
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
	public void setTraining(Training training) {
		this.training = training;
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
