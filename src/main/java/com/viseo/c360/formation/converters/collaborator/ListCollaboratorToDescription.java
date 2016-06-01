package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;

import java.util.ArrayList;
import java.util.List;


public class ListCollaboratorToDescription {

    public ListCollaboratorToDescription() {
    }

    public List<CollaboratorDescription> convert(List<Collaborator> sourceList) {
        List<CollaboratorDescription> listDto = new ArrayList<>();
        for (Collaborator collaborator : sourceList) {
            listDto.add(new CollaboratorToDescription().convert(collaborator));
        }
        return listDto;
    }

}
