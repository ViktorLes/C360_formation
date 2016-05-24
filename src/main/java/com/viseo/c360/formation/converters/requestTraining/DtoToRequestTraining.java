package com.viseo.c360.formation.converters.requestTraining;


import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;

public class DtoToRequestTraining implements Converter <RequestTraining, RequestTrainingDTO>{

    @Inject
    TrainingDAO trainingDAO;
    CollaboratorDAO collaboratorDAO;

    public RequestTraining convert(RequestTrainingDTO dto){
        RequestTraining domain = new RequestTraining();
        Training training = trainingDAO.getTraining(dto.getTraining().getId());
        if(training == null) throw new PersistentObjectNotFoundException();
    }

}
