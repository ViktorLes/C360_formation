package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class ListCollaboratorToDTO{

    public ListCollaboratorToDTO (){

    }

    public List<CollaboratorDescription> convert(List<Collaborator> sourceList) {
        List<CollaboratorDescription> listDto = new ArrayList<>();
        for(Collaborator collaborator : sourceList){
            listDto.add(conversionService.convert(collaborator, CollaboratorDescription.class));
        }
        return listDto;
    }

}
