package com.viseo.c360.formation.domain.collaborateur;

import com.viseo.c360.formation.domain.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Collaborator extends BaseEntity {

	public static class Regex{
		public static final String personnalIdNumber = "[A-Z0-9]";
		public static final String lastName = "[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]";
		public static final String firstName = "[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]";
	}

	@NotNull
	@Size(min=3, max=20)
	@Pattern(regexp= Collaborator.Regex.personnalIdNumber +"*")
	String personnalIdNumber;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp= Collaborator.Regex.lastName +"*")
	String lastName;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp= Collaborator.Regex.firstName +"*")
	String firstName;
	
	public Collaborator() {
		super();
	}

	public String getPersonnalIdNumber() {
		return personnalIdNumber;
	}
	public void setPersonnalIdNumber(String personnalIdNumber) {
		this.personnalIdNumber = personnalIdNumber.trim();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName.replaceAll("( )+", " ").trim();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName.replaceAll("( )+", " ").trim();
	}
}
