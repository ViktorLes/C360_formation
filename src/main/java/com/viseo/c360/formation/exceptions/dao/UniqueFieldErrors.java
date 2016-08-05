package com.viseo.c360.formation.exceptions.dao;

public enum UniqueFieldErrors {
    COLLABORATOR_ID_NUMBER_PERSISTED("personnalIdNumber", "IdNumberPersisted"),
    COLLABORATOR_EMAIL_PERSISTED("email","EmailPersisted"),
    TRAINING_TITLE("trainingTitle","TrainingTitlePersisted"),
    TOPIC_NAME("name", "TopicNamePersisted");

    String field;
    String message;

    UniqueFieldErrors(String field, String label){
        this.field = field;
        this.message = label;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public boolean matches(String field){
        return field.toLowerCase().equals(this.field.toLowerCase());
    }
}
