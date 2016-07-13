package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;

import com.viseo.c360.formation.converters.collaborator.CollaboratorToDescription;
import com.viseo.c360.formation.converters.topic.DescriptionToTopic;
import com.viseo.c360.formation.converters.topic.TopicToDescription;
import com.viseo.c360.formation.converters.training.DescriptionToTraining;
import com.viseo.c360.formation.converters.training.TrainingToDescription;
import com.viseo.c360.formation.converters.trainingsession.DescriptionToTrainingSession;
import com.viseo.c360.formation.converters.trainingsession.TrainingSessionToDescription;
import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;

import com.viseo.c360.formation.dto.training.TopicDescription;
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
    public long addTraining(@RequestBody TrainingDescription myTrainingDescription) {
        try {
            long topicId = myTrainingDescription.getTopicDescription().getId();
            Topic topic = trainingDAO.getTopic(topicId);
            if (topic == null) throw new PersistentObjectNotFoundException(topicId, Topic.class);
            Training training = (trainingDAO.addTraining(new DescriptionToTraining().convert(myTrainingDescription, topic)));
            if (training == null) {
                return 0;
            }
            return training.getId();
        } catch (ConversionException | PersistentObjectNotFoundException e) {
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
     * Topic
     ***/
    @RequestMapping(value = "${endpoint.topics}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addTopic(@RequestBody TopicDescription myTopicDescription) {
        try {
            return (trainingDAO.addTopic(new DescriptionToTopic().convert(myTopicDescription)));
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.topics}", method = RequestMethod.GET)
    @ResponseBody
    public List<TopicDescription> getAllTopics() {
        try {
            return new TopicToDescription().convert(trainingDAO.getAllTopics());
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
                throw new PersistentObjectNotFoundException(myTrainingSessionDescription.getTrainingDescription().getId(), Training.class);
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

    //Update Training Session
    @RequestMapping(value = "${endpoint.sessions}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateTrainingSession(@RequestBody TrainingSessionDescription trainingSessionDescription) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(trainingSessionDescription.getId());
            TrainingSession newTrainingSession = new DescriptionToTrainingSession().convert(trainingSessionDescription, trainingSession.getTraining());
            if (trainingSession == null)
                throw new PersistentObjectNotFoundException(trainingSession.getId(), TrainingSession.class);
            if (!trainingDAO.isThereOneSessionTrainingAlreadyPlanned(newTrainingSession)
                    && newTrainingSession.getBeginning().before(newTrainingSession.getEnding())
                    )
            {
                trainingDAO.updateTrainingSession(
                        trainingSession,
                        newTrainingSession
                );
                return true;
            }
            return false;
        } catch (PersistentObjectNotFoundException | ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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