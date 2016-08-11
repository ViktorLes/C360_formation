package com.viseo.c360.formation.dto.collaborator;

import com.viseo.c360.formation.dto.BaseDTO;

public class CollaboratorToken extends BaseDTO {

    String personnalIdNumber;
    String lastName;
    String firstName;
    String roles;

    public CollaboratorToken() {
            this.roles = "USER";
    }

    public String getPersonnalIdNumber() {
        return personnalIdNumber;
    }

    public void setPersonnalIdNumber(String personnalIdNumber) {
        this.personnalIdNumber = personnalIdNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return this.firstName+" "+this.getLastName();
    }


}
