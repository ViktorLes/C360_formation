package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.exceptions.C360Exception;
import com.viseo.c360.formation.exceptions.dao.PersistentObjectNotFoundException;
import com.viseo.c360.formation.exceptions.dao.TrainingSessionErrors;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TrainingDAO {

    @PersistenceContext
    EntityManager em;

    /***
     * Training
     ***/
    public Training getTraining(long id) {
        return em.find(Training.class, id);
    }

    @Transactional
    public Training addTraining(Training training) throws PersistenceException {
        em.persist(training);
        em.flush();
        return training;
    }

    public List<Training> getAllTrainings() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select a from Training a", Training.class).getResultList();
    }

    /***
     * Topic
     ***/
    @Transactional
    public Topic addTopic(Topic topic) throws PersistenceException {
        em.persist(topic);
        em.flush();
        return topic;
    }

    public Topic getTopic(long id){
        return em.find(Topic.class,id);
    }

    public List<Topic> getAllTopics(){
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select t from Topic t", Topic.class).getResultList();
    }



    /***
     * Session Training
     ***/
    public List<TrainingSession> getSessionByTraining(long myTrainingId) {
        em.setFlushMode(FlushModeType.COMMIT);
        Query q = em.createQuery("select s from TrainingSession s where s.training.id=:myTrainingId")
                .setParameter("myTrainingId", myTrainingId);
        return q.getResultList();
    }


    public List<TrainingSession> getAllTrainingSessions() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select s from TrainingSession s", TrainingSession.class).getResultList();
    }

    public TrainingSession getSessionTraining(long id) throws PersistentObjectNotFoundException{
            TrainingSession trainingSession = em.find(TrainingSession.class, id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            trainingSession.getCollaborators().size();
            return trainingSession;
    }

    @Transactional
    public TrainingSession addSessionTraining(TrainingSession trainingSession) {
        if (this.isThereOneSessionTrainingAlreadyPlanned(trainingSession)){
            throw new C360Exception(TrainingSessionErrors.TRAINING_SESSION_ALREADY_PLANNED.getMessage());
        }
        if(!trainingSession.getBeginning().before(trainingSession.getEnding())) {
            throw new C360Exception(TrainingSessionErrors.TRAINING_SESSION_INCORRECT_DATES.getMessage());
        }
        em.persist(trainingSession);
        em.flush();
        return trainingSession;
    }

    @Transactional
    public TrainingSession updateTrainingSession(TrainingSession trainingSession, TrainingSession trainingSessionTemp){
        if (this.isThereOneSessionTrainingAlreadyPlanned(trainingSessionTemp)){
            throw new C360Exception(TrainingSessionErrors.TRAINING_SESSION_ALREADY_PLANNED.getMessage());
        }
        if(!trainingSessionTemp.getBeginning().before(trainingSessionTemp.getEnding())) {
            throw new C360Exception(TrainingSessionErrors.TRAINING_SESSION_INCORRECT_DATES.getMessage());
        }
        trainingSession = em.merge(trainingSession);
        trainingSession.setBeginning(trainingSessionTemp.getBeginning());
        trainingSession.setEnding(trainingSessionTemp.getEnding());
        trainingSession.setLocation(trainingSessionTemp.getLocation());
        em.flush();
        return trainingSession;
    }

    public boolean isThereOneSessionTrainingAlreadyPlanned(TrainingSession trainingSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        Query q = em.createQuery("select s from TrainingSession s " +
                "where s.training=:training and s.id != :trainingSessionId and" +
                "( (s.beginning >= :beginning and s.beginning < :ending) or" +
                "(s.ending >= :beginning and s.ending <= :ending) or " +
                "(s.beginning <= :beginning and s.ending >= :ending) )"
                ).setParameter("trainingSessionId", trainingSession.getId())
                .setParameter("training", trainingSession.getTraining())
                .setParameter("beginning", trainingSession.getBeginning())
                .setParameter("ending", trainingSession.getEnding());
        Collection<TrainingSession> list = (Collection<TrainingSession>) q.getResultList();
        return !list.isEmpty();
    }
}
