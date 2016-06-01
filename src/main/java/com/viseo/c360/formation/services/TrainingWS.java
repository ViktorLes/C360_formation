package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.core.convert.ConversionService;
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

	@Inject
	ConversionService conversionService;
	
	/*** Training ***/
	@RequestMapping(value="${endpoint.trainings}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTraining(@Valid @RequestBody Training myTraining, BindingResult bindingResult){
		if(!(bindingResult.hasErrors())
			&& !trainingDAO.isTrainingPersisted(myTraining.getTrainingTitle()))
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
    public boolean addTrainingSession(@Valid @RequestBody TrainingSessionDescription myTrainingSessionDescription, BindingResult bindingResult){
		if(!bindingResult.hasErrors()) {
			try {
				Training training = trainingDAO.getTraining(myTrainingSessionDescription.getTrainingDescription().getId());
				if(training == null) throw new PersistentObjectNotFoundException(myTrainingSessionDescription.getTrainingDescription().getId(), TrainingSession.class);
				TrainingSession myTrainingSession = new DescriptionToTrainingSession().convert(myTrainingSessionDescription,training);
				if(!trainingDAO.isThereOneSessionTrainingAlreadyPlanned(myTrainingSession)
					&& myTrainingSession.getBeginning().before(myTrainingSession.getEnding())
				){
					trainingDAO.addSessionTraining(myTrainingSession);
					return true;
				}
			} catch(PersistentObjectNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
    }

	@RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSessionDescription> getTrainingSessions(){
		return conversionService.convert(trainingDAO.getAllTrainingSessions(),List.class);
	}

	@RequestMapping(value = "${endpoint.sessionsbyid}", method = RequestMethod.GET)
	@ResponseBody
    public List<TrainingSessionDescription> getTrainingSessionsByTraining(@PathVariable String id){
		return conversionService.convert(trainingDAO.getSessionByTraining(Long.parseLong(id)),List.class);
	}
}

