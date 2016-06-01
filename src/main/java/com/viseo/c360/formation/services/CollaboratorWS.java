package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;

import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.viseo.c360.formation.dao.CollaboratorDAO;


@RestController
public class CollaboratorWS {

    @Inject
    CollaboratorDAO collaboratorDAO;
    @Inject
    TrainingDAO trainingDAO;
    @Inject
    ConversionService conversionService;

    @RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addCollaborator(@Valid @RequestBody CollaboratorDescription myCollaboratorDescription, BindingResult bindingResult) {
        if (!(bindingResult.hasErrors()) && !collaboratorDAO.isPersonnalIdNumberPersisted(myCollaboratorDescription.getPersonnalIdNumber())) {
            try {
                collaboratorDAO.addCollaborator(conversionService.convert(myCollaboratorDescription, Collaborator.class));
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.GET)
    @ResponseBody
    public List<CollaboratorDescription> getAllCollaborators() {
        try {
            return conversionService.convert(collaboratorDAO.getAllCollaborators(), List.class);
        } catch (ConversionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "${endpoint.requests}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@Valid @RequestBody RequestTrainingDescription
                                              myRequestTrainingDescription, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                RequestTraining myRequestTraining =
                        conversionService.convert(myRequestTrainingDescription, RequestTraining.class);
                collaboratorDAO.addRequestTraining(myRequestTraining);
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping(value = "${endpoint.collaboratorsbysession}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateCollaboratorsTrainingSession(@PathVariable Long id, @Valid @RequestBody List<CollaboratorDescription> collaboratorDescriptions, BindingResult bindingResult) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            collaboratorDAO.updateCollaboratorsTrainingSession(trainingSession, conversionService.convert(collaboratorDescriptions, List.class));
            return true;
        }
        catch (ConversionException e) {
            e.printStackTrace();
        }
        catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
