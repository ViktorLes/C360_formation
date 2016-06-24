package com.viseo.formation.tests;

import com.viseo.c360.formation.dao.CollaboratorDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.services.CollaboratorWS;

import com.viseo.fake.db.FakeDAOFacade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCollaboratorDAO {
    FakeDAOFacade fakeDaoFacade = null;
    CollaboratorDAO collaboratorDAO = null;
    CollaboratorWS collaboratorWS = null;
    CollaboratorDescription collaboratorDescriptionThomas = null;
    CollaboratorDescription collaboratorDescriptionBayrek = null;

    void prepareDto(){
        collaboratorDescriptionThomas = new CollaboratorDescription();
        collaboratorDescriptionThomas.setPersonnalIdNumber("TLE3473");
        collaboratorDescriptionThomas.setLastName("Lecomte");
        collaboratorDescriptionThomas.setFirstName("Thomas");
        collaboratorDescriptionBayrek = new CollaboratorDescription();
        collaboratorDescriptionBayrek.setPersonnalIdNumber("BMO3473");
        collaboratorDescriptionBayrek.setLastName("Mokni");
        collaboratorDescriptionBayrek.setFirstName("Bayrek");
    }

    @Before
    public void setUp() {
        prepareDto();
        collaboratorWS = new CollaboratorWS();
        collaboratorDAO = new CollaboratorDAO();
        collaboratorWS.collaboratorDAO = collaboratorDAO;
        fakeDaoFacade = new FakeDAOFacade();
        fakeDaoFacade.declareEntityClasses(Collaborator.class);
        fakeDaoFacade.declareEntityClasses(RequestTraining.class);
        fakeDaoFacade.registerFilter("select c from Collaborator c", (facade, entity, paramRegistry) -> {
            return entity instanceof Collaborator;
        });
        fakeDaoFacade.registerFilter("select c from Collaborator c where c.personnalIdNumber = :personnalIdNumber", (facade, entity, paramRegistry) -> {
            if(!(entity instanceof Collaborator)) return false;
            Collaborator collaborator = (Collaborator) entity;
            return collaborator.getPersonnalIdNumber().equals(paramRegistry.get("personnalIdNumber"));
        });
        collaboratorDAO.daoFacade = fakeDaoFacade;
    }

    @Test
    public void testAddCollaborator(){
        collaboratorWS.addCollaborator(collaboratorDescriptionThomas);
        collaboratorWS.addCollaborator(collaboratorDescriptionBayrek);
        List<CollaboratorDescription> collaboratorList = new ArrayList<>();
        collaboratorList.add(collaboratorDescriptionThomas);
        collaboratorList.add(collaboratorDescriptionBayrek);
        Assert.assertEquals(collaboratorList.size(),collaboratorWS.getAllCollaborators().size());
    }


}
