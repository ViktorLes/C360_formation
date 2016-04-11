package com.viseo.c360.formation.domain.formation;


import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.json.deserializer.formation.SessionFormationDeserializer;



@JsonDeserialize(using = SessionFormationDeserializer.class)
@Entity
public class SessionFormation {
	
	public static final String regextitreFormation = "[A-Z0-9]";
	public static final String regexDate = "[0-9]";
	public static final String regexHeureDeb = "[0-9]";
	public static final String regexHeureFin= "[0-9]"; 
	public static final String regexLieu = "[A-Z]";
	
	
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	@ManyToOne
	Formation formation;
	
	@Temporal(TemporalType.DATE)
	Date dateDebut;
	
	@Temporal(TemporalType.DATE)
	Date dateFin;
	
	@Temporal(TemporalType.TIME)
	Date heureDebut;
	
	@Temporal(TemporalType.TIME)
	Date heureFin;
	
	String lieu;
	
	

	public SessionFormation() {
		super();
	}
	public long getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public Formation getFormation() {
		return formation;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public Date getHeureFin() {
		return heureFin;
	}
	public String getLieu() {
		return lieu;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

}
