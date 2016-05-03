package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.FormationDAO;

@RestController
public class FormationWS {

	@Inject
	FormationDAO formationDAO;
	
	//Training
	@RequestMapping(value="${endpoint.formations}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addFormation(@Valid @RequestBody Training myTraining, BindingResult bindingResult){
		if(!(bindingResult.hasErrors())
			&& !formationDAO.isFormationPersistent(myTraining.getTitleTraining()))
		{
			formationDAO.addFormation(myTraining);
			return true;
    	}
		return false;
	}

	@RequestMapping(value = "${endpoint.formations}", method = RequestMethod.GET)
	@ResponseBody
    public List<Training> ReadFormation(){
		return formationDAO.getAllFormation();
	}
	
	//TrainingSession
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addSessionFormation(@Valid @RequestBody TrainingSession myTrainingSession, BindingResult bindingResult){
		if(!(bindingResult.hasErrors() )
			&& !formationDAO.isThereOneSessionFormationAlreadyPlanned(myTrainingSession)
			&& formationDAO.hasCorrectDates(myTrainingSession))
		{
			formationDAO.addSessionFormation(myTrainingSession);
			return true;
		}
		return false;
    }

	@RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSession> readSessionFormation(){
		return formationDAO.getAllSessionFormation();
	}

	@RequestMapping(value = "${endpoint.sessionsbyid}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSession> readSessionByFormation(@PathVariable String id){
		return formationDAO.getSessionByFormation(Long.parseLong(id));
	}
}	