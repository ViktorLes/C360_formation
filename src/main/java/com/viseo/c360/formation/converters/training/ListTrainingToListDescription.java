package com.viseo.c360.formation.converters.training;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;

import java.util.ArrayList;
import java.util.List;

public class ListTrainingToListDescription {
    public ListTrainingToListDescription() {
    }

    public List<TrainingDescription> convert(List<Training> trainings) {
        List<TrainingDescription> listDescription = new ArrayList<TrainingDescription>();
        for(Training myTraining : trainings){
            listDescription.add(new TrainingToDescription().convert(myTraining));
        }
        return listDescription;
    }
}
