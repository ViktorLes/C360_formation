package com.viseo.c360.formation.converters.collaborator;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class ListCollaboratorToDTO implements Converter <List<Collaborator>, List<CollaboratorDTO>> {

    ConversionService conversionService;

    public ListCollaboratorToDTO (ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public List<CollaboratorDTO> convert(List<Collaborator> sourceList) {
        List<CollaboratorDTO> listDto = new ArrayList<>();
        for(Collaborator collaborator : sourceList){
            listDto.add(conversionService.convert(collaborator, CollaboratorDTO.class));
        }
        return listDto;
    }

}
