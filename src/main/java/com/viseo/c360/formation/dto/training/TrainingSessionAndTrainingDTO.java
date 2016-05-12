package com.viseo.c360.formation.dto.training;

import com.viseo.c360.formation.domain.training.Training;

import javax.validation.constraints.NotNull;

public class TrainingSessionAndTrainingDTO extends BaseTrainingSessionDTO {
    @NotNull
    Training training;
    public TrainingSessionAndTrainingDTO() {
        super();
    }
    public Training getTraining() {
        return training;
    }
    public void setTraining(Training training) {
        this.training = training;
    }
}
