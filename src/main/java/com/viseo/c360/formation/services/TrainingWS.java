package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;

import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.converters.training.ListTrainingToListDescription;
import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.converters.trainingsession.ListTrainingSessionToListDescription;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingDescription;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.springframework.web.bind.annotation.*;

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
        if (!trainingDAO.isTrainingPersisted(myTrainingDescription.getTrainingTitle())) {
            trainingDAO.addTraining(new DescriptionToTraining().convert(myTrainingDescription));
            return true;
        }
        return false;
    }

    @RequestMapping(value = "${endpoint.trainings}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingDescription> getAllTrainingsDescriptions() {
        return new ListTrainingToListDescription().convert(trainingDAO.getAllTrainings());
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
        return new ListTrainingSessionToListDescription().convert(trainingDAO.getAllTrainingSessions());
    }

    @RequestMapping(value = "${endpoint.sessionsbyid}", method = RequestMethod.GET)
    @ResponseBody
    public List<TrainingSessionDescription> getTrainingSessionsByTraining(@PathVariable String id) {
        return new ListTrainingSessionToListDescription().convert(trainingDAO.getSessionByTraining(Long.parseLong(id)));
    }
}

