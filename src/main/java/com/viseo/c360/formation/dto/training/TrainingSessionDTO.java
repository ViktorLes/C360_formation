package com.viseo.c360.formation.dto.training;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TrainingSessionDTO extends BaseTrainingSessionDTO {
    @NotNull
    @Min(value = 1)
    long training;
    public TrainingSessionDTO(){
        super();
    }
    public long getTraining() {
        return training;
    }
    public void setTraining(long training) {
        this.training = training;
    }
}
