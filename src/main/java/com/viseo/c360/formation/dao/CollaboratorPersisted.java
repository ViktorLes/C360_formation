package com.viseo.c360.formation.dao;

public enum CollaboratorPersisted {
    ID_NUMBER_PERSISTED("personnalIdNumber", "IdNumberPersisted"),
    EMAIL_PERSISTED("email","EmailPersisted"),
    NOT_PERSISTED("", "NotPersisted");

    String field;
    String label;

    CollaboratorPersisted(String field, String label){
        this.field = field;
        this.label = label;
    }

    public String getField() {
        return field;
    }

    public String getLabel() {
        return label;
    }

    public boolean matches(String field){
        return field.toLowerCase().equals(this.field.toLowerCase());
    }
}
