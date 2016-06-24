package com.viseo.c360.formation.converters.training;


import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;

public class DescriptionToTraining {

    public DescriptionToTraining() {
    }

    public Training convert(TrainingDescription dto) {
        Training domain = new Training();

        domain.setId(dto.getId());
        domain.setTrainingTitle(dto.getTrainingTitle());
        domain.setNumberHalfDays(dto.getNumberHalfDays());
        return domain;
    }
}
