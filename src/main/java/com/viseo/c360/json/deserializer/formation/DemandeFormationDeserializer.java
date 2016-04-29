package com.viseo.c360.json.deserializer.formation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.viseo.c360.formation.dao.CollaborateurDAO;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.collaborateur.Collaborateur;
import com.viseo.c360.formation.domain.collaborateur.DemandeFormation;
import com.viseo.c360.formation.domain.formation.Formation;

import javax.inject.Inject;
import java.io.IOException;

import java.util.List;

public class DemandeFormationDeserializer extends JsonDeserializer<DemandeFormation> {
    @Inject
    CollaborateurDAO collaborateurDAO;
    @Inject
    FormationDAO formationDAO;

    @Override
    public DemandeFormation deserialize(JsonParser parser, DeserializationContext context) throws JsonProcessingException, IOException {

        DemandeFormation dmf = new DemandeFormation();
        ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);

        //Formation
        Formation f = formationDAO.getFormation(node.get("formation").asLong());
        /*if(f == null) {
            System.err.println("Impossible d'obtenir l'objet 'Formation' d'Id : "+Long.toString(idFormation)+".");
            throw new SessionFormationDeserializer.FormationDAOException("Impossible d'obtenir l'objet 'Formation' d'Id : "+Long.toString(idFormation)+".");
        }*/
        dmf.setFormation(f);

        Collaborateur c = collaborateurDAO.getCollaborateur(node.get("collaborateur").asLong());
        dmf.setFormation(f);

        //List<SessionFormation> listSession = formationDAO.

        try {
            /*
            debut = formatterDate.parse(node.get("debut").asText());
            fin = formatterDate.parse(node.get("fin").asText());

            sf.setDebut(debut);
            sf.setFin(fin);
            */
        } catch (Exception e) {
            e.printStackTrace();
           // throw new SessionFormationDeserializer.FormationDAOException("Problème de format de date/heure.");
        }

        return dmf;
    }
}
