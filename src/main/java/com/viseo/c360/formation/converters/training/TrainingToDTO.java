package com.viseo.c360.formation.converters.training;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;


import javax.inject.Inject;

public class TrainingToDTO {

    public TrainingToDTO() {
    }

    public TrainingDescription convert(Training source) {
        TrainingDescription dto = new TrainingDescription();
        dto.setId(source.getId());
        dto.setNumberHalfDays(source.getNumberHalfDays());
        dto.setTrainingTitle(source.getTrainingTitle());
        return dto;
    }
}