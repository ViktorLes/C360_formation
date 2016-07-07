package com.viseo.c360.formation.dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import com.viseo.c360.formation.domain.training.TrainingSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;

@Repository
public class CollaboratorDAO {

    @PersistenceContext
    EntityManager em;

    //collaborateur
    @Transactional
    public boolean addCollaborator(Collaborator collaborator) {
        if (!this.isPersonnalIdNumberOrEmailPersisted(collaborator.getPersonnalIdNumber(),collaborator.getEmail())) {
                em.merge(collaborator);
                return true;
            }
            return false;
    }

    public boolean isPersonnalIdNumberOrEmailPersisted(String personnalIdNumber, String email) {
        em.setFlushMode(FlushModeType.COMMIT);
        Collection<Collaborator> listCollaborator =
                (Collection<Collaborator>) em.createQuery(
                        "select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber or c.email = :email" , Collaborator.class)
                        .setParameter(personnalIdNumber,"personnalIdNumber").setParameter(email,"email").getResultList();
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

