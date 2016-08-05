package com.viseo.c360.formation.dao;

import java.util.*;

import javax.inject.Inject;

import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.fake.db.DAOFacade;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.viseo.fake.db.DAOFacade.Parameter.param;

import com.viseo.c360.formation.domain.collaborator.Collaborator;

@Repository
public class CollaboratorDAO {

    @Inject
    DAOFacade daoFacade;

    //collaborateur
    @Transactional
    public boolean addCollaborator(Collaborator collaborator) {
        if (!this.isPersonnalIdNumberPersisted(collaborator.getPersonnalIdNumber())) {
            daoFacade.merge(collaborator);
                return true;
            }
            return false;
    }

    public boolean isPersonnalIdNumberPersisted(String personnalIdNumber) {
        List<Collaborator> listCollaborator =
                daoFacade.getList(
                        "select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber",
                        param("personnalIdNumber", personnalIdNumber));

        return !listCollaborator.isEmpty();
    }

    public List<Collaborator> getAllCollaborators() {
        return daoFacade.getList("select c from Collaborator c");
    }

    public Collaborator getCollaborator(long id) {
        return daoFacade.find(Collaborator.class, id);
    }

    //request training
    @Transactional
    public void addRequestTraining(RequestTraining requestTraining) {
        daoFacade.persist(requestTraining);
    }

    @Transactional
    public void affectCollaboratorsTrainingSession(TrainingSession myTrainingSession, List<Collaborator> collaborators) {
        myTrainingSession = daoFacade.merge(myTrainingSession);
        myTrainingSession.removeCollaborators();
        for (Collaborator myCollaborator : collaborators) {
            myTrainingSession.addCollaborator(myCollaborator);
        }
    }

    public List<Collaborator> getNotAffectedCollaborators(TrainingSession myTrainingSession) {
        if (!myTrainingSession.getCollaborators().isEmpty()) {
            List<Collaborator> listCollaborator = daoFacade.getList(
                    "select c from Collaborator c where c NOT IN :listCollaborators",
                    param("listCollaborators", myTrainingSession.getCollaborators()));
            return listCollaborator;
        } else {
            List<Collaborator> listCollaborator = daoFacade.getList("select c from Collaborator c");
            return listCollaborator;
        }
    }

    public List<Collaborator> getCollaboratorsRequestingBySession(TrainingSession myTrainnigSession) {
        Set<Collaborator> listCollaborator = new HashSet<Collaborator>(daoFacade.getList(
                "select c from RequestTraining r Inner Join r.collaborator c Inner Join r.listSession s Where s = :session",
                param("session", myTrainnigSession)));
        listCollaborator.addAll(daoFacade.getList(
                "select c from RequestTraining r Inner Join r.collaborator c Where r.training = :training",
                param("training", myTrainnigSession.getTraining())));
        listCollaborator.removeAll(daoFacade.getList(
                "select c from TrainingSession s Inner Join s.collaborators c Where s.training = :training",
                param("training", myTrainnigSession.getTraining())));
        return new ArrayList<>(listCollaborator);
    }
}

