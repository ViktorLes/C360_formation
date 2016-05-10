package com.viseo.c360.formation.domain.training;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.viseo.c360.formation.domain.BaseEntity;

@Entity
public class TrainingSession extends BaseEntity {

	@ManyToOne
	Training training;

	Date beginning;

	Date ending;
	
	String location;

	public TrainingSession() {
		super();
	}
	public Training getTraining() {
		return training;
	}
	public Date getBeginning() {
		return beginning;
	}
	public Date getEnding() {
		return ending;
	}
	public String getLocation() {
		return location;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public void setBeginning(Date dateDebut) {
		this.beginning = dateDebut;
	}
	public void setEnding(Date dateFin) {
		this.ending = dateFin;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
