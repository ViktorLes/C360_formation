package com.viseo.c360.formation.dto.training;


import com.sun.istack.internal.NotNull;
import com.viseo.c360.formation.dto.BaseDTO;

import java.util.List;

public class TopicDescription extends BaseDTO{

    List<TrainingDescription>trainingDescriptions;

    public TopicDescription() {
    }

    public List<TrainingDescription> getTrainingDescriptions() {
        return trainingDescriptions;
    }

    public void setTrainingDescriptions(List<TrainingDescription> trainingDescriptions) {
        this.trainingDescriptions = trainingDescriptions;
    }
}