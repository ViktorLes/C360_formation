package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;

import java.util.ArrayList;
import java.util.List;

public class ListDescriptionToListTrainingSession {
    public ListDescriptionToListTrainingSession() {
    }

    public List<TrainingSession> convert(List<TrainingSessionDescription> listDto) {
        List<TrainingSession> listTrainingSession = new ArrayList<TrainingSession>();
        for (TrainingSessionDescription myTrainingSessionDescription : listDto) {
            listTrainingSession.add(
                    new DescriptionToTrainingSession().convert(
                            myTrainingSessionDescription,
                            new DescriptionToTraining().convert(myTrainingSessionDescription.getTrainingDescription())
                    )
            );
        }
        return listTrainingSession;
    }

}
