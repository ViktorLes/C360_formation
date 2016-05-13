package com.viseo.c360.formation.converters.trainingsession;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ListTrainingSessionToDTO implements Converter<List<TrainingSession>, List<TrainingSessionDTO>> {

    ConversionService conversionService;

    public ListTrainingSessionToDTO(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public List<TrainingSessionDTO> convert(List<TrainingSession> sourceList) {
        List<TrainingSessionDTO> listDto = new ArrayList<>();
        for(TrainingSession trainingSession : sourceList){
           listDto.add(conversionService.convert(trainingSession, TrainingSessionDTO.class));
        }
        return listDto;
    }
}
