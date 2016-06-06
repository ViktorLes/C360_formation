package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;


import java.util.ArrayList;
import java.util.List;

public class ListTrainingSessionToListDescription {

    public ListTrainingSessionToListDescription() {
    }

    public List<TrainingSessionDescription> convert(List<TrainingSession> sourceList) {
        List<TrainingSessionDescription> listDescription = new ArrayList<>();
        for(TrainingSession trainingSession : sourceList){
           listDescription.add(new TrainingSessionToDescription().convert(trainingSession));
        }
        return listDescription;
    }
}