package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
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
    public boolean addRequestTraining(@RequestBody RequestTrainingDTO requestTrainingDto){
		/*if(!(bindingResult.hasErrors()) && !collaboratorDAO.isPersonnalIdNumberPersisted(myCollaborator.getPersonnalIdNumber())){
			collaboratorDAO.addCollaborator(myCollaborator);
			return true;
		}*/
		RequestTraining myRequestTraining = new RequestTraining();
		myRequestTraining.setCollaborator(collaboratorDAO.getCollaboratorById(requestTrainingDto.getCollaborator()) );
		myRequestTraining.setTraining(trainingDAO.getTraining(requestTrainingDto.getTraining()));
		for(long i : requestTrainingDto.getTrainingSessions()){
			myRequestTraining.addListSession(trainingDAO.getSessionTraining(i));
		}
		collaboratorDAO.addRequestTraining(myRequestTraining);
		//System.out.println(String.valueOf(requestTrainingDto.getTraining()));
		return false;
    }
}
