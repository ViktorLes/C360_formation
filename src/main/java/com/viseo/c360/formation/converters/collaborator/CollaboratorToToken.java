package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorToken;

public class CollaboratorToToken {
    public CollaboratorToToken() {
    }

    public CollaboratorToken convert(Collaborator source) {
        CollaboratorToken dto = new CollaboratorToken();
        dto.setId(source.getId());
        dto.setVersion(source.getVersion());
        dto.setPersonnalIdNumber(source.getPersonnalIdNumber());
        dto.setLastName(source.getLastName());
        dto.setFirstName(source.getFirstName());
        dto.setRoles(source.getRoles());
        return dto;
    }
}
