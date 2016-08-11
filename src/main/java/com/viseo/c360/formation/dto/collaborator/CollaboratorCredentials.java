package com.viseo.c360.formation.dto.collaborator;


import com.viseo.c360.formation.dto.BaseDTO;

public class CollaboratorCredentials extends BaseDTO {
    String email;
    String password;

    public CollaboratorCredentials() {
    }

    public CollaboratorCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

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
