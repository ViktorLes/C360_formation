package com.viseo.c360.formation.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
<<<<<<< HEAD
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;
=======
import com.viseo.c360.formation.dto.collaborator.CollaboratorDTO;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDTO;
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9

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
<<<<<<< HEAD
    public boolean addCollaborator(@Valid @RequestBody CollaboratorDescription myCollaboratorDescription, BindingResult bindingResult) {
        if (!(bindingResult.hasErrors()) && !collaboratorDAO.isPersonnalIdNumberPersisted(myCollaboratorDescription.getPersonnalIdNumber())) {
            try {
                collaboratorDAO.addCollaborator(conversionService.convert(myCollaboratorDescription, Collaborator.class));
=======
    public boolean addCollaborator(@Valid @RequestBody CollaboratorDTO myCollaboratorDto, BindingResult bindingResult) {
        if (!(bindingResult.hasErrors()) && !collaboratorDAO.isPersonnalIdNumberPersisted(myCollaboratorDto.getPersonnalIdNumber())) {
            try {
                collaboratorDAO.addCollaborator(conversionService.convert(myCollaboratorDto, Collaborator.class));
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.GET)
    @ResponseBody
<<<<<<< HEAD
    public List<CollaboratorDescription> getAllCollaborators() {
=======
    public List<CollaboratorDTO> getAllCollaborators() {
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
        try {
            return conversionService.convert(collaboratorDAO.getAllCollaborators(), List.class);
        } catch (ConversionException e) {
            e.printStackTrace();
        }
        return null;
    }

<<<<<<< HEAD
    @RequestMapping(value = "${endpoint.requests}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@Valid @RequestBody RequestTrainingDescription
                                              myRequestTrainingDescription, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                RequestTraining myRequestTraining =
                        conversionService.convert(myRequestTrainingDescription, RequestTraining.class);
=======
	@RequestMapping(value = "${endpoint.collaboratorsbysession}",method = RequestMethod.PUT)
	@ResponseBody
	public boolean affectCollaboratorsTrainingSession(@PathVariable Long id, @Valid @RequestBody List<Collaborator> collaborators, BindingResult bindingResult){
		try {
			 TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
			 if(trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
			 collaboratorDAO.affectCollaboratorsTrainingSession(trainingSession, collaborators);
			 return true;
		} catch (PersistentObjectNotFoundException e) {
			e.printStackTrace();
		}
		return false;
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
		}
		return new ArrayList<>();
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
		}
		return new ArrayList<>();
	}

    @RequestMapping(value = "${endpoint.requests}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@Valid @RequestBody RequestTrainingDTO myRequestTrainingDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                RequestTraining myRequestTraining =
                        conversionService.convert(myRequestTrainingDTO, RequestTraining.class);
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
                collaboratorDAO.addRequestTraining(myRequestTraining);
                return true;
            } catch (ConversionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
<<<<<<< HEAD

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
=======
>>>>>>> 5d51c637147120bde988d153a12ca2c9d2a1d1f9
}

