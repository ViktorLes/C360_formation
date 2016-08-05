package com.viseo.c360.formation.dto;


public abstract class BaseDTO {

    long id;

    long version;

    public BaseDTO() {
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
}