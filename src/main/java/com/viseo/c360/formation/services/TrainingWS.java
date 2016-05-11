package com.viseo.c360.formation.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDTO;
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
    public boolean addTrainingSession(@Valid @RequestBody TrainingSessionDTO myTrainingSessionDto, BindingResult bindingResult){
		if(!bindingResult.hasErrors()) {
			TrainingSession myTrainingSession = new TrainingSession();
			Training training = trainingDAO.getTraining(myTrainingSessionDto.getTraining());
			if(training == null) return false;
			myTrainingSession.setTraining(training);
			myTrainingSession.setLocation(myTrainingSessionDto.getLocation());
			SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
			try {
				myTrainingSession.setBeginning(
						formatterDate.parse(
								myTrainingSessionDto.getBeginning() + "|" + myTrainingSessionDto.getBeginningTime()));
				myTrainingSession.setEnding(
						formatterDate.parse(
							myTrainingSessionDto.getEnding()+ "|" + myTrainingSessionDto.getEndingTime()));
				if(!trainingDAO.isThereOneSessionTrainingAlreadyPlanned(myTrainingSession)
					&& myTrainingSession.getBeginning().before(myTrainingSession.getEnding())
				){
					trainingDAO.addSessionTraining(myTrainingSession);
					return true;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
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