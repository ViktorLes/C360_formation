package com.viseo.c360.formation.domain.collaborator;

import com.viseo.c360.formation.domain.BaseEntity;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Collaborator extends BaseEntity {

	@NotNull
	@Size(min=3, max=20)
	@Pattern(regexp=CollaboratorDescription.Regex.PERSONNAL_ID_NUMBER)
	String personnalIdNumber;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp=CollaboratorDescription.Regex.LAST_NAME+"*")
	String lastName;
	
	@NotNull
	@Size(min=2, max=125)
	@Pattern(regexp=CollaboratorDescription.Regex.FIRST_NAME+"*")
	String firstName;

	@NotNull
	@Email
	String email;

	@NotNull
	String password;

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
	public String getEmail() {return email;}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}