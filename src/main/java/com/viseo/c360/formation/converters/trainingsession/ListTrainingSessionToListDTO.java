package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;


import java.util.ArrayList;
import java.util.List;

public class ListTrainingSessionToListDTO{

    public ListTrainingSessionToListDTO() {
    }

    public List<TrainingSessionDescription> convert(List<TrainingSession> sourceList) {
        List<TrainingSessionDescription> listDto = new ArrayList<>();
        for(TrainingSession trainingSession : sourceList){
           listDto.add(conversionService.convert(trainingSession, TrainingSessionDescription.class));
        }
        return listDto;
    }
}