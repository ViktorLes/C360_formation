package com.viseo.c360.formation.converters.training;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;


import java.util.ArrayList;
import java.util.List;

public class TrainingToDescription {

    public TrainingToDescription() {
    }

    public TrainingDescription convert(Training source) {
        TrainingDescription dto = new TrainingDescription();
        dto.setId(source.getId());
        dto.setNumberHalfDays(source.getNumberHalfDays());
        dto.setTrainingTitle(source.getTrainingTitle());
        return dto;
    }

    public List<TrainingDescription> convert(List<Training> trainings) {
        List<TrainingDescription> listDescription = new ArrayList<TrainingDescription>();
        for(Training myTraining : trainings){
            listDescription.add(convert(myTraining));
        }
        return listDescription;
    }
}