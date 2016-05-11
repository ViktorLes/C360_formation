package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.AffectationTrainingSession;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.AffectationTrainingSessionDTO;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDTO;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaboratorDAO;


@RestController
public class CollaboratorWS {

	@Inject
	CollaboratorDAO collaboratorDAO;
	@Inject
	TrainingDAO trainingDAO;
		
	@RequestMapping(value = "${endpoint.collaborators}",method = RequestMethod.POST)
    @ResponseBody
    public boolean addCollaborator(@Valid @RequestBody Collaborator myCollaborator, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !collaboratorDAO.isPersonnalIdNumberPersisted(myCollaborator.getPersonnalIdNumber())){
			collaboratorDAO.addCollaborator(myCollaborator);
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.GET)
	@ResponseBody
    public List<Collaborator> getAllCollaborators(){
		return collaboratorDAO.getAllCollaborators();
	}
	
	@RequestMapping(value = "${endpoint.requests}",method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@Valid @RequestBody RequestTrainingDTO requestTrainingDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) return false;
		RequestTraining myRequestTraining = new RequestTraining();
		Collaborator collaborator = collaboratorDAO.getCollaborator(requestTrainingDto.getCollaborator());
		Training training = trainingDAO.getTraining(requestTrainingDto.getTraining());
		if(collaborator == null || training == null) return false;
		myRequestTraining.setCollaborator(collaborator);
		myRequestTraining.setTraining(training);
		for(long i : requestTrainingDto.getTrainingSessions()){
			TrainingSession trainingSession = trainingDAO.getSessionTraining(i);
			if(trainingSession == null) return false;
			myRequestTraining.addListSession(trainingSession);
		}
		collaboratorDAO.addRequestTraining(myRequestTraining);
		return true;
    }

	@RequestMapping(value = "${endpoint.affectations}",method = RequestMethod.POST)
	@ResponseBody
	public boolean addAffectationTrainingSession(@Valid @RequestBody AffectationTrainingSessionDTO affectationTrainingSessionDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()) return false;
		AffectationTrainingSession myAffectationTrainingSession = new AffectationTrainingSession();
		TrainingSession trainingSession = trainingDAO.getSessionTraining(affectationTrainingSessionDto.getTrainingSession());
		if(trainingSession == null) return false;
		myAffectationTrainingSession.setTrainingSession(trainingSession);
		for(long i : affectationTrainingSessionDto.getCollaborators()){
			Collaborator collaborator = collaboratorDAO.getCollaborator(i);
			if(collaborator == null) return false;
			myAffectationTrainingSession.addCollaborator(collaborator);
		}
		collaboratorDAO.addAffectationTrainingSession(myAffectationTrainingSession);
		return true;
	}
}
