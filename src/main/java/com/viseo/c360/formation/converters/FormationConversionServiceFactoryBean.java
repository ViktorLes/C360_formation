package com.viseo.c360.formation.converters;

import com.viseo.c360.formation.converters.requestTraining.DtoToRequestTraining;
import com.viseo.c360.formation.converters.trainingsession.ListTrainingSessionToListDTO;
import com.viseo.c360.formation.converters.trainingsession.TrainingSessionToDTO;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

public class FormationConversionServiceFactoryBean extends ConversionServiceFactoryBean {

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = getObject();
        ConverterRegistry registry = (ConverterRegistry) conversionService;
        registry.addConverter(new ListTrainingSessionToListDTO(conversionService));
        registry.addConverter(new TrainingSessionToDTO(conversionService));
        registry.addConverter(new DtoToRequestTraining(conversionService));
    }
}