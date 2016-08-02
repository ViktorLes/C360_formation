package com.viseo.c360.formation.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.exceptions.C360Exception;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;

@Repository
public class CollaboratorDAO {

    @PersistenceContext
    EntityManager em;

    @Inject
    ExceptionUtil exceptionUtil;

    //collaborateur
    @Transactional
    public CollaboratorPersisted addCollaborator(Collaborator collaborator) {
        try{
            em.persist(collaborator);
            em.flush();
            return CollaboratorPersisted.NOT_PERSISTED;
        }catch(PersistenceException pe){
           if(pe.getCause() instanceof ConstraintViolationException){
               String field = exceptionUtil.getUniqueField((ConstraintViolationException)pe.getCause());
               for(CollaboratorPersisted collaboratorPersisted : CollaboratorPersisted.values()) {
                   if(collaboratorPersisted.matches(field)) {
                       throw new C360Exception(collaboratorPersisted);
                   }
               }
           }
           throw new C360Exception("PersistenceException from CollaboratorDAO.addCollaborator");
        }
    }

    public Collaborator getCollaboratorByLoginPassword(String personnalEmail,String personnalPassword){
        em.setFlushMode(FlushModeType.COMMIT);
        Collaborator registredUser =
                (Collaborator) em.createQuery(
                        "select c from Collaborator c where c.email = :personnalEmail and c.password = :personnalPassword",Collaborator.class)
                    .setParameter("personnalEmail",personnalEmail).setParameter("personnalPassword",personnalPassword)
                    .getSingleResult();
        return registredUser;
    }

    public boolean isPersonnalIdNumberPersisted(String personnalIdNumber) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Collaborator> listCollaborator =
                (Collection<Collaborator>) em.createQuery(
                        "select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber" , Collaborator.class)
                        .setParameter("personnalIdNumber",personnalIdNumber).getResultList();
        return !listCollaborator.isEmpty();
    }

    public boolean isEmailPersisted(String email) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Collaborator> listCollaborator =
                (Collection<Collaborator>) em.createQuery(
                        "select c from Collaborator c where c.email = :email" , Collaborator.class)
                        .setParameter("email",email).getResultList();
        return !listCollaborator.isEmpty();
    }

    public List<Collaborator> getAllCollaborators() {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.createQuery("select c from Collaborator c", Collaborator.class).getResultList();
    }

    public Collaborator getCollaborator(long id) {
        em.setFlushMode(FlushModeType.COMMIT);
        return em.find(Collaborator.class, id);
    }

    //request training
    @Transactional
    public void addRequestTraining(RequestTraining requestTraining) {
        em.persist(requestTraining);
    }

    @Transactional
    public void affectCollaboratorsTrainingSession(TrainingSession myTrainingSession, List<Collaborator> collaborators) {
        myTrainingSession = em.merge(myTrainingSession);
        myTrainingSession.removeCollaborators();
        for (Collaborator myCollaborator : collaborators) {
            myTrainingSession.addCollaborator(myCollaborator);
        }
    }

    public List<Collaborator> getNotAffectedCollaborators(TrainingSession myTrainingSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        if (!myTrainingSession.getCollaborators().isEmpty()) {
            List<Collaborator> listCollaborator = (List<Collaborator>) em.createQuery(
                    "select c from Collaborator c where c NOT IN :listCollaborators", Collaborator.class)
                    .setParameter("listCollaborators", myTrainingSession.getCollaborators()).getResultList();
            return listCollaborator;
        } else {
            List<Collaborator> listCollaborator = (List<Collaborator>) em.createQuery(
                    "select c from Collaborator c", Collaborator.class).getResultList();
            return listCollaborator;
        }
    }

    public List<Collaborator> getCollaboratorsRequestingBySession(TrainingSession myTrainnigSession) {
        em.setFlushMode(FlushModeType.COMMIT);
        Set<Collaborator> listCollaborator = new HashSet<Collaborator>(em.createQuery(
                "select c from RequestTraining r Inner Join r.  collaborator c Inner Join r.listSession s Where s = :session")
                .setParameter("session", myTrainnigSession).getResultList());
        listCollaborator.addAll(em.createQuery(
                "select c from RequestTraining r Inner Join r.collaborator c Where r.training = :training")
                .setParameter("training", myTrainnigSession.getTraining()).getResultList());
        listCollaborator.removeAll(em.createQuery(
                "select c from TrainingSession s Inner Join s.collaborators c Where s.training = :training")
                .setParameter("training", myTrainnigSession.getTraining()).getResultList());
        return new ArrayList<>(listCollaborator);
    }
}

