package com.viseo.c360.formation.converters.training;


import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingDTO;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DtoToTraining implements Converter <TrainingDTO, Training> {

    TrainingDAO trainingDAO;

    @Inject
    public Training convert (TrainingDTO dto){
        Training domain = new Training();
        try{
            domain.setId(dto.getId());
            domain.setTrainingTitle(dto.getTrainingTitle());
            domain.setNumberHalfDays(dto.getNumberHalfDays());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ConversionFailedException(
                TypeDescriptor.valueOf(TrainingDTO.class),
                TypeDescriptor.valueOf(Training.class),
                dto,
                new Throwable(e.getMessage())
        );
    }
    return domain;
    }
}
