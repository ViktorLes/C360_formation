package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ListDtoToListTrainingSession implements Converter <List<TrainingSessionDTO>, List<TrainingSession>>{
    ConversionService conversionService;

    public ListDtoToListTrainingSession(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    public List<TrainingSession> convert(List<TrainingSessionDTO> ListDTO) {
        List<TrainingSession> list = new ArrayList<>();
        for(TrainingSessionDTO trainingSessionDTO : ListDTO)
        {
            list.add(conversionService.convert(trainingSessionDTO, TrainingSession.class));
        }
        return list;
    }
}
