package com.viseo.c360.formation.domain.collaborator;

import com.viseo.c360.formation.domain.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Collaborator extends BaseEntity {


	@NotNull
	@Size(min=3, max=20)
	String personnalIdNumber;
	
	@NotNull
	@Size(min=2, max=125)
	String lastName;
	
	@NotNull
	@Size(min=2, max=125)
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
