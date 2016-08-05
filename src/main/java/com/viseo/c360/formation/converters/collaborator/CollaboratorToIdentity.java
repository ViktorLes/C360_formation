package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorIdentity;

public class CollaboratorToIdentity {
    public CollaboratorIdentity convert(Collaborator source){
        CollaboratorIdentity dto = new CollaboratorIdentity();
        dto.setId(source.getId());
        dto.setVersion(source.getVersion());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        return dto;
    }
}
