package com.viseo.c360.formation.converters.collaborator;


import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;


public class CollaboratorToDescription {

    public CollaboratorToDescription() {
    }

    public CollaboratorDescription convert(Collaborator source) {
        CollaboratorDescription dto = new CollaboratorDescription();
        dto.setId(source.getId());
        dto.setPersonnalIdNumber(source.getPersonnalIdNumber());
        dto.setLastName(source.getLastName());
        dto.setFirstName(source.getFirstName());
        return dto;
    }
}
