package com.viseo.c360.formation.converters.collaborator;


import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;

import java.util.ArrayList;
import java.util.List;

public class ListDescriptionToListCollaborator {

    public ListDescriptionToListCollaborator() {
    }

    public List<Collaborator> convert(List<CollaboratorDescription> sourceList) {
        List<Collaborator> listCollaborator = new ArrayList<>();
        for (CollaboratorDescription collaboratorDescription : sourceList) {
            listCollaborator.add(new DescriptionToCollaborator().convert(collaboratorDescription));
        }
        return listCollaborator;
    }

}
