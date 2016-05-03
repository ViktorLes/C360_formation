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

import com.viseo.c360.formation.dao.TrainingDAO;

@RestController
public class TrainingWS {

	@Inject
	TrainingDAO trainingDAO;
	
	/*** Training ***/
	@RequestMapping(value="${endpoint.trainings}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTraining(@Valid @RequestBody Training myTraining, BindingResult bindingResult){
		if(!(bindingResult.hasErrors())
			&& !trainingDAO.isTrainingPersisted(myTraining.getTitleTraining()))
		{
			trainingDAO.addTraining(myTraining);
			return true;
    	}
		return false;
	}

	@RequestMapping(value = "${endpoint.trainings}", method = RequestMethod.GET)
	@ResponseBody
    public List<Training> getAllTrainings(){
		return trainingDAO.getAllTrainings();
	}
	
	/*** TrainingSession ***/
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTrainingSession(@Valid @RequestBody TrainingSession myTrainingSession, BindingResult bindingResult){
		if(!(bindingResult.hasErrors() )
			&& !trainingDAO.isThereOneSessionTrainingAlreadyPlanned(myTrainingSession)
			&& trainingDAO.hasCorrectDates(myTrainingSession))
		{
			trainingDAO.addSessionTraining(myTrainingSession);
			return true;
		}
		return false;
    }

	@RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSession> getTrainingSessions(){
		return trainingDAO.getAllTrainingSessions();
	}

	@RequestMapping(value = "${endpoint.sessionsbyid}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSession> getTrainingSessionsByTraining(@PathVariable String id){
		return trainingDAO.getSessionByTraining(Long.parseLong(id));
	}
}	