package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;

import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.converters.training.TrainingToDescription;
import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.converters.trainingsession.TrainingSessionToDescription;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;

import org.springframework.core.convert.ConversionException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;


import com.viseo.c360.formation.dao.TrainingDAO;

@RestController
public class TrainingWS {

    @Inject
    TrainingDAO trainingDAO;

    /***
     * Training
     ***/
    @RequestMapping(value = "${endpoint.trainings}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTraining(@RequestBody TrainingDescription myTrainingDescription) {
        try {
            return (trainingDAO.addTraining(new DescriptionToTraining().convert(myTrainingDescription)));
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.trainings}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingDescription> getAllTrainingsDescriptions() {
        return new TrainingToDescription().convert(trainingDAO.getAllTrainings());
    }

    /***
     * TrainingSession
     ***/
    @RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTrainingSession(@RequestBody TrainingSessionDescription myTrainingSessionDescription) {
        try {
            Training training = trainingDAO.getTraining(myTrainingSessionDescription.getTrainingDescription().getId());
            if (training == null)
                throw new PersistentObjectNotFoundException(myTrainingSessionDescription.getTrainingDescription().getId(), TrainingSession.class);
            TrainingSession myTrainingSession = new DescriptionToTrainingSession().convert(myTrainingSessionDescription, training);
            if (!trainingDAO.isThereOneSessionTrainingAlreadyPlanned(myTrainingSession)
                    && myTrainingSession.getBeginning().before(myTrainingSession.getEnding())
                    ) {
                trainingDAO.addSessionTraining(myTrainingSession);
                return true;
            }
        } catch (PersistentObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingSessionDescription> getTrainingSessionsDescriptions() {
        return new TrainingSessionToDescription().convert(trainingDAO.getAllTrainingSessions());
    }

    @RequestMapping(value = "${endpoint.sessionsbyid}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingSessionDescription> getTrainingSessionsByTraining(@PathVariable String id) {
        return new TrainingSessionToDescription().convert(trainingDAO.getSessionByTraining(Long.parseLong(id)));
    }
}