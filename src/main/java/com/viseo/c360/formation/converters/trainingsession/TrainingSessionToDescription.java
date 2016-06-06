package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.converters.training.TrainingToDescription;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;

import java.text.SimpleDateFormat;

public class TrainingSessionToDescription {

    public TrainingSessionToDescription() {
    }

    public TrainingSessionDescription convert(TrainingSession source) {
        TrainingSessionDescription dto = new TrainingSessionDescription();
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        dto.setId(source.getId());
        dto.setTrainingDescription(new TrainingToDescription().convert(source.getTraining()));
        dto.setBeginning(formatterDate.format(source.getBeginning()));
        dto.setBeginningTime(formatterTime.format(source.getBeginning()));
        dto.setEnding(formatterDate.format(source.getEnding()));
        dto.setEndingTime(formatterTime.format(source.getEnding()));
        dto.setLocation(source.getLocation());
        return dto;
    }
}
