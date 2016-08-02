package com.viseo.c360.formation.services;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.viseo.c360.formation.converters.collaborator.CollaboratorToDescription;
import com.viseo.c360.formation.converters.collaborator.DescriptionToCollaborator;
import com.viseo.c360.formation.converters.requestTraining.DescriptionToRequestTraining;
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.collaborator.Collaborator;
import com.viseo.c360.formation.domain.collaborator.RequestTraining;
import com.viseo.c360.formation.domain.training.Topic;
import com.viseo.c360.formation.domain.training.TrainingSession;
import com.viseo.c360.formation.dto.collaborator.CollaboratorDescription;
import com.viseo.c360.formation.dto.collaborator.RequestTrainingDescription;
import com.viseo.c360.formation.exceptions.PersistentObjectNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.convert.ConversionException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.viseo.c360.formation.dao.CollaboratorDAO;


@RestController
public class CollaboratorWS {

    @Inject
    CollaboratorDAO collaboratorDAO;
    @Inject
    TrainingDAO trainingDAO;
    //@Inject
    //AuthenticationManager authenticationManager;


    @RequestMapping(value = "${endpoint.user}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, CollaboratorDescription> getUserByLoginPassword(@RequestBody CollaboratorDescription myCollaboratorDescription) {
        try {
            InitializeMap();
            Collaborator c = collaboratorDAO.getCollaboratorByLoginPassword(myCollaboratorDescription.getEmail(), myCollaboratorDescription.getPassword());
            CollaboratorDescription user = new CollaboratorToDescription().convert(c);
            Key key = MacProvider.generateKey();
            String compactJws = Jwts.builder()
                    .setSubject(user.getFirstName())
                    .claim("lastName", user.getLastName())
                    .claim("roles", user.getIsAdmin())
                    .claim("id",user.getId())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            Map currentUserMap = new HashMap<>();
            putUserInCache(compactJws, user);
            currentUserMap.put("userConnected", compactJws);
            return currentUserMap;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static ConcurrentHashMap<String, CollaboratorDescription> mapUserCache;

    private void InitializeMap() {
        if (mapUserCache == null)
            mapUserCache = new ConcurrentHashMap<String, CollaboratorDescription>();
    }

    private void putUserInCache(String token, CollaboratorDescription user) {
        mapUserCache.put(token, user);
    }

    @RequestMapping(value = "${endpoint.userdisconnect}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteDisconnectedUserFromCache(@RequestBody String token) {
        try {
            mapUserCache.remove(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addCollaborator(@RequestBody CollaboratorDescription myCollaboratorDescription) {
        try {
            String response = collaboratorDAO.addCollaborator(new DescriptionToCollaborator().convert(myCollaboratorDescription));
            Map map = new HashMap<>();
            map.put("response", response);
            return map;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaborators}", method = RequestMethod.GET)
    @ResponseBody
    public List<CollaboratorDescription> getAllCollaborators() {
        try {
            return new CollaboratorToDescription().convert(collaboratorDAO.getAllCollaborators());
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaboratorsNotAffectedBySession}", method = RequestMethod.GET)
    @ResponseBody
    public List<CollaboratorDescription> getNotAffectedCollaboratorsList(@PathVariable Long id) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            return new CollaboratorToDescription().convert(collaboratorDAO.getNotAffectedCollaborators(trainingSession));
        } catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaboratorsAffectedBySession}", method = RequestMethod.GET)
    @ResponseBody
    public List<CollaboratorDescription> getAffectedCollaboratorsList(@PathVariable Long id) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            return new CollaboratorToDescription().convert(trainingSession.getCollaborators());
        } catch (PersistentObjectNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "${endpoint.requests}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addRequestTraining(@RequestBody RequestTrainingDescription myRequestTrainingDescription) {
        try {
            Topic topic = trainingDAO.getTopic(myRequestTrainingDescription.getTrainingDescription().getTopicDescription().getId());
            Collaborator myCollaborator = collaboratorDAO.getCollaborator(myRequestTrainingDescription.getCollaboratorIdentity().getId());
            RequestTraining myRequestTraining = new DescriptionToRequestTraining().convert(myRequestTrainingDescription, myCollaborator, topic);
            collaboratorDAO.addRequestTraining(myRequestTraining);
            return true;
        } catch (ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaboratorsbysession}", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateCollaboratorsTrainingSession(@PathVariable Long id, @RequestBody List<CollaboratorDescription> collaboratorDescriptions) {
        try {
            TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
            if (trainingSession == null) throw new PersistentObjectNotFoundException(id, TrainingSession.class);
            collaboratorDAO.affectCollaboratorsTrainingSession(trainingSession, new DescriptionToCollaborator().convert(collaboratorDescriptions));
            return true;
        } catch (PersistentObjectNotFoundException | ConversionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "${endpoint.collaboratorsRequestingListByTrainingSession}", method = RequestMethod.GET)
    @ResponseBody
    public List<CollaboratorDescription> getCollaboratorsRequestingListByTrainingSession(@PathVariable Long id) {
        TrainingSession trainingSession = trainingDAO.getSessionTraining(id);
        return new CollaboratorToDescription().convert(collaboratorDAO.getCollaboratorsRequestingBySession(trainingSession));
    }
}