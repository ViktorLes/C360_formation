package com.viseo.c360.formation.converters.training;


import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DtoToTraining {

    public DtoToTraining() {
    }

    public Training convert(TrainingDescription dto) {
        Training domain = new Training();

        domain.setId(dto.getId());
        domain.setTrainingTitle(dto.getTrainingTitle());
        domain.setNumberHalfDays(dto.getNumberHalfDays());
        return domain;
    }
}
