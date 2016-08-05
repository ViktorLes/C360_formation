package com.viseo.c360.formation.converters.training;


import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DescriptionToTraining {

    public DescriptionToTraining() {
    }

    public Training convert(TrainingDescription dto, Topic topic) {
        Training domain = new Training();
        domain.setId(dto.getId());
        domain.setVersion(dto.getVersion());
        domain.setTrainingTitle(dto.getTrainingTitle());
        domain.setNumberHalfDays(dto.getNumberHalfDays());
        domain.setTopic(topic);
        return domain;
    }
}
