package com.viseo.c360.formation.domain.formation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.viseo.c360.formation.domain.formation.Formation;

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
	
	@NotNull
	@Pattern(regexp=SessionFormation.regextitreFormation+"*")
	String titreFormation;
	
	@NotNull
	@Pattern(regexp=SessionFormation.regexDate+"*")
	String date;

	@NotNull
	@Size(min=8, max=18)
	@Pattern(regexp=SessionFormation.regexHeureDeb+"*")
	String heureDeb;
	
	@Size(min=8, max=18)
	@Pattern(regexp=SessionFormation.regexHeureFin+"*")
	String heureFin;
	
	@NotNull
	@Pattern(regexp=SessionFormation.regexLieu+"*")
	String lieu;
	
	public SessionFormation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getTitreformation() {
		return titreFormation;
	}

	public void setTitreformation(String titreFormation) {
		this.titreFormation = titreFormation;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeureDeb() {
		return heureDeb;
	}

	public void setHeureDeb(String heureDeb) {
		this.heureDeb = heureDeb;
	}

	public String getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
}
