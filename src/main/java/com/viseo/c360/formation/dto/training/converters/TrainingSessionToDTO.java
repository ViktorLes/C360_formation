package com.viseo.c360.formation.dto.training.converters;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
import org.springframework.core.convert.converter.Converter;
import java.text.SimpleDateFormat;

public class TrainingSessionToDTO implements Converter<TrainingSession, TrainingSessionDTO> {
    public TrainingSessionDTO convert(TrainingSession source) {
        TrainingSessionDTO dto = new TrainingSessionDTO();
        dto.setTraining(source.getTraining().getId());
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
}
