package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

import javax.inject.Inject;

public class CollaboratorToDTO {

    public CollaboratorDescription convert (Collaborator source){
        CollaboratorDescription dto = new CollaboratorDescription();
        try {
            dto.setId(source.getId());
            dto.setPersonnalIdNumber(source.getPersonnalIdNumber());
            dto.setLastName(source.getLastName());
            dto.setFirstName(source.getFirstName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(Collaborator.class),
                    TypeDescriptor.valueOf(CollaboratorDescription.class),
                    source,
                    new Throwable(e.getMessage())
            );
        }
    return dto;
    }
}
