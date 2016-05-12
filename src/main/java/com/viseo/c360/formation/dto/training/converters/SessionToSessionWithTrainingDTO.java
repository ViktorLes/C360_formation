package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SessionToSessionWithTrainingDTO implements Converter<TrainingSession, TrainingSessionDTO> {

    @Inject
    TrainingDAO trainingDAO;
    @Inject
    ConversionService conversionService;

    public TrainingSessionDTO convert(TrainingSession source) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        Training training = trainingDAO.getTraining(source.getTraining().getId());
        if(training == null) try {
            throw new PersistentObjectNotFoundException(source.getTraining().getId(), Training.class);
        } catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(TrainingSession.class),
                    TypeDescriptor.valueOf(TrainingSessionDTO.class),
                    source,
                    new Throwable(e.getMessage())
            );
        }
        dto.setTrainingId(training.getId());
        dto.setTrainingTitle(training.getTrainingTitle());
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
        dto.setBeginning(formatterDate.format(source.getBeginning()));
        dto.setBeginningTime(formatterTime.format(source.getBeginning()));
        dto.setEnding(formatterDate.format(source.getEnding()));
        dto.setEndingTime(formatterTime.format(source.getEnding()));
        dto.setLocation(source.getLocation());
        for(Collaborator collaborator : source.getCollaborators()){
            dto.getCollaborators().add(collaborator.getId());
        }
        return dto;
    }

    public class ListTSessionToListDtoTSessionAndTraining implements Converter<List<TrainingSession>, List<SessionWithTrainingDTO>>{
        public List<TrainingSessionDTO> convert(List<TrainingSession> source) {
            List<SessionWithTrainingDTO> listDto = new ArrayList<>();
            for(TrainingSession session : source){

            }
            return listDto;
        }
    }
}
