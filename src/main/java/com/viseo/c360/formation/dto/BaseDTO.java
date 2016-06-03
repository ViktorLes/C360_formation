package com.viseo.c360.formation.dto;


import javax.persistence.Id;

public class BaseDTO {

    @Id
    long id;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
