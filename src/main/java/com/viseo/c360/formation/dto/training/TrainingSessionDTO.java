package com.viseo.c360.formation.dto.training;

public class TrainingSessionDTO {
    long training;
    String beginning;
    String ending;
    String location;
    public TrainingSessionDTO() {
    }
    public String getBeginning() {
        return beginning;
    }
    public void setBeginning(String beginning) {
        this.beginning = beginning;
    }
    public String getEnding() {
        return ending;
    }
    public void setEnding(String ending) {
        this.ending = ending;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public long getTraining() {
        return training;
    }
    public void setTraining(long training) {
        this.training = training;
    }
}
