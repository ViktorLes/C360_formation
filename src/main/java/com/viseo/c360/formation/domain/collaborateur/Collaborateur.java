package com.viseo.c360.formation.domain.collaborateur;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Collaborateur {
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	@NotNull
	@Size(min=3, max=20)
	@Pattern(regexp="[A-Z0-9]*")
	String matricule;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp="[a-zA-Z-'.áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ ]*")
	String nom;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp="[a-zA-Z-'.áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ ]*")
	String prenom;
	
	public Collaborateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	public long getVersion() {
		return version;
	}
	public String getMatricule() {
		return matricule;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule.trim();
	}
	public void setNom(String nom) {
		this.nom = nom.replaceAll("( )+", " ").trim();
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom.replaceAll("( )+", " ").trim();
	}
	
}
