package com.viseo.c360.formation.converters.training;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDTO;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;


import javax.inject.Inject;

public class TrainingToDTO implements Converter <Training, TrainingDTO> {

    @Inject
    TrainingDAO trainingDAO;

    public TrainingDTO convert(Training source) {
        TrainingDTO dto = new TrainingDTO();
        try {
            dto.setId(source.getId());
            dto.setNumberHalfDays(source.getNumberHalfDays());
            dto.setTrainingTitle(source.getTrainingTitle());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(Training.class),
                    TypeDescriptor.valueOf(TrainingDTO.class),
                    source,
                    new Throwable(e.getMessage())
            );
        }
        return dto;
    }
}