package com.viseo.c360.formation.converters.collaborator;


import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;

import javax.inject.Inject;

    public class DtoToCollaborator {

    public Collaborator convert(CollaboratorDescription dto){
        Collaborator domain = new Collaborator();
        try {
            if(dto.getId() > 0) domain.setId(dto.getId());
            domain.setFirstName(dto.getFirstName());
            domain.setLastName(dto.getLastName());
            domain.setPersonnalIdNumber(dto.getPersonnalIdNumber());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ConversionFailedException(
                    TypeDescriptor.valueOf(CollaboratorDescription.class),
                    TypeDescriptor.valueOf(Collaborator.class),
                    dto,
                    new Throwable(e.getMessage())
            );
        }
        return domain;
    }
}