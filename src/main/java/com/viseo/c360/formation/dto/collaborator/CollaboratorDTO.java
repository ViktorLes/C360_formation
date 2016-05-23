package com.viseo.c360.formation.dto.collaborator;


import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CollaboratorDTO {

    @Id
    long id;

    @NotNull
    @Pattern(regexp="[A-Z0-9]")
    @Valid
    String personnalIdNumber;

    @NotNull
    @Pattern(regexp="[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]")
    @Valid
    String lastName;

    @NotNull
    @Pattern(regexp="[a-zA-Z-'. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]")
    @Valid
    String firstName;

    public CollaboratorDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
