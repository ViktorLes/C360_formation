package com.viseo.c360.formation.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.ConversionService;
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
    public boolean addCollaborator(@RequestBody CollaboratorDescription myCollaboratorDescription) {
        if (!collaboratorDAO.isPersonnalIdNumberPersisted(myCollaboratorDescription.getPersonnalIdNumber())) {
            try {
                collaboratorDAO.addCollaborator(conversionService.convert(myCollaboratorDescription, Collaborator.class));
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

	@RequestMapping(value = "${endpoint.collaboratorsbysession}",method = RequestMethod.PUT)
	@ResponseBody
	public boolean affectCollaboratorsTrainingSession(@PathVariable Long id, @RequestBody List<Collaborator> collaborators){
		try {
			 TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
			 if(trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
			 collaboratorDAO.affectCollaboratorsTrainingSession(trainingSession, collaborators);
			 return true;
		} catch (PersistentObjectNotFoundException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@RequestMapping(value = "${endpoint.collaboratorsNotAffectedBySession}", method = RequestMethod.GET)
	@ResponseBody
	public List<Collaborator> getNotAffectedCollaboratorsList(@PathVariable Long id){
		try {
			TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
			if(trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
			return collaboratorDAO.getNotAffectedCollaborators(trainingSession);
		} catch (PersistentObjectNotFoundException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@RequestMapping(value = "${endpoint.collaboratorsAffectedBySession}", method = RequestMethod.GET)
	@ResponseBody
	public List<Collaborator> getAffectedCollaboratorsList(@PathVariable Long id){
		try {
			TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
			if(trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
			return trainingSession.getCollaborators();
		} catch (PersistentObjectNotFoundException e) {
			e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

    @RequestMapping(value = "${endpoint.requests}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@RequestBody RequestTrainingDescription myRequestTrainingDTO) {
            try {
                RequestTraining myRequestTraining =
                        conversionService.convert(myRequestTrainingDTO, RequestTraining.class);
                collaboratorDAO.addRequestTraining(myRequestTraining);
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }

    @RequestMapping(value = "${endpoint.collaboratorsbysession}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateCollaboratorsTrainingSession(@PathVariable Long id, @RequestBody List<CollaboratorDescription> collaboratorDescriptions) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            collaboratorDAO.affectCollaboratorsTrainingSession(trainingSession, conversionService.convert(collaboratorDescriptions, List.class));
            return true;
        } catch (PersistentObjectNotFoundException | ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

