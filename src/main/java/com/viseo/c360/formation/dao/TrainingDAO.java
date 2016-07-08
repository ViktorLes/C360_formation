package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.training.TrainingSessionDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.core.convert.ConversionException;
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
    public Training addTraining(Training training) {
        if (!this.isTrainingPersisted(training.getTrainingTitle())) {
            return em.merge(training);
        }
        return null;
    }

    public boolean isTrainingPersisted(String trainingTitle) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Training> listTrainings =
                (Collection<Training>) em.createQuery("select t from Training t where upper(t.trainingTitle) = :trainingTitle")
                        .setParameter("trainingTitle", trainingTitle.toUpperCase()).getResultList();
        return !listTrainings.isEmpty();
    }

    public List<Training> getAllTrainings() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select a from Training a", Training.class).getResultList();
    }

    /***
     * Topic
     ***/
    @Transactional
    public boolean addTopic (Topic topic){
        if(!this.isTopicPersisted((topic.getName()))) {
            topic = em.merge(topic);
            return true;
        }
        return false;
    }

    public Topic getTopic(long id){
        return em.find(Topic.class,id);
    }

    public List<Topic> getAllTopics(){
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select t from Topic t", Topic.class).getResultList();
    }

    public boolean isTopicPersisted(String name) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Topic> listTopic =
                (Collection<Topic>) em.createQuery("select t from Topic t where t.name = :name")
                        .setParameter("name", name).getResultList();
        return !listTopic.isEmpty();
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

    @Transactional
    public void addSessionTraining(TrainingSession trainingSession) {
        em.persist(trainingSession);
    }

    @Transactional
    public TrainingSession getSessionTraining(long id) {
        try {
            TrainingSession trainingSession = em.find(TrainingSession.class, id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            trainingSession.getCollaborators().size();
            return trainingSession;
        } catch (PersistentObjectNotFoundException | ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public TrainingSession updateTrainingSession(TrainingSession trainingSession, TrainingSession trainingSessionTemp){
        trainingSession = em.merge(trainingSession);
        trainingSession.setBeginning(trainingSessionTemp.getBeginning());
        trainingSession.setEnding(trainingSessionTemp.getEnding());
        trainingSession.setLocation(trainingSessionTemp.getLocation());
        return trainingSession;
    }

    public boolean isThereOneSessionTrainingAlreadyPlanned(TrainingSession trainingSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrainingSession> q = cb.createQuery(TrainingSession.class);
        Root<TrainingSession> c = q.from(TrainingSession.class);
        q.select(c).where(cb.equal(c.get("training"), trainingSession.getTraining().getId()),
                cb.or(
                        cb.and(
                                cb.greaterThanOrEqualTo(c.<Date>get("beginning"), trainingSession.getBeginning()),
                                cb.lessThan(c.<Date>get("beginning"), trainingSession.getEnding())
                        ),
                        cb.and(
                                cb.greaterThan(c.<Date>get("ending"), trainingSession.getBeginning()),
                                cb.lessThanOrEqualTo(c.<Date>get("ending"), trainingSession.getEnding())
                        ),
                        cb.and(
                                cb.lessThanOrEqualTo(c.<Date>get("beginning"), trainingSession.getBeginning()),
                                cb.greaterThanOrEqualTo(c.<Date>get("ending"), trainingSession.getEnding())
                        )
                )
        );
        Collection<TrainingSession> list = (Collection<TrainingSession>) em.createQuery(q).getResultList();
        return !list.isEmpty();
    }
}
