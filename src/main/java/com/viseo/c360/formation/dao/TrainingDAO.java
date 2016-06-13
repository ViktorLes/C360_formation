package com.viseo.c360.formation.dao;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;
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
    public void addTraining(String trainingTitle, int numberHalfDays) {
        Training training = new Training();
        training.setTrainingTitle(trainingTitle);
        training.setNumberHalfDays(numberHalfDays);
        em.persist(training);
    }

    @Transactional
    public void addTraining(Training training) {
        em.persist(training);
    }

    public boolean isTrainingPersisted(String trainingTitle) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Training> listTrainings =
                (Collection<Training>) em.createQuery("select t from Training t where t.trainingTitle = :trainingTitle")
                        .setParameter("trainingTitle", trainingTitle).getResultList();
        return !listTrainings.isEmpty();
    }

    public List<Training> getAllTrainings() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select a from Training a", Training.class).getResultList();
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
