package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ListDescriptionToListTrainingSession {

    public ListDescriptionToListTrainingSession() {
    }
    public List<TrainingSession> convert(List<TrainingSessionDescription> ListDTO) {
        List<TrainingSession> list = new ArrayList<>();
        for(TrainingSessionDescription trainingSessionDescription : ListDTO)
        {
            list.add();
        }
        return list;
    }
}
