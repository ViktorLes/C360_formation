package com.viseo.c360.json.deserializer.formation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.formation.Training;
import com.viseo.c360.formation.domain.formation.TrainingSession;

public class SessionFormationDeserializer extends JsonDeserializer<TrainingSession> {

	public class FormationDAOException extends JsonProcessingException{

		private static final long serialVersionUID = 1L;

		public FormationDAOException(String message) {
			super(message);
		}
	}
	
	@Inject
	FormationDAO formationDAO;
	
	@Override
	public TrainingSession deserialize(JsonParser parser, DeserializationContext context) throws JsonProcessingException, IOException {

		SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
		Date debut = null;
		Date fin = null;
	
		TrainingSession sf = new TrainingSession();
		ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        
        //Training
        Long idFormation = node.get("formation").asLong();
        Training f = formationDAO.getFormation(idFormation);
        if(f == null) { 
        	System.err.println("Impossible d'obtenir l'objet 'Training' d'Id : "+Long.toString(idFormation)+".");
        	throw new FormationDAOException("Impossible d'obtenir l'objet 'Training' d'Id : "+Long.toString(idFormation)+".");
        }
		sf.setTraining(f);
		
		//dates et heures
        try {
        	debut = formatterDate.parse(node.get("debut").asText());
        	fin = formatterDate.parse(node.get("fin").asText());
        	
//        	if(!debut.before(fin)) {
//        		System.err.println("L'ordre des dates ne concorde pas ( debut : "+debut.toString()+" <= fin : "+fin.toString());
//        		throw new FormationDAOException("L'ordre des dates ne concorde pas ( debut : "+debut.toString()+" <= fin : "+fin.toString());
//        	}
        	
        	sf.setBeginning(debut);
        	sf.setEnding(fin);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new FormationDAOException("Problème de format de date/heure.");
		}
        
        //lieu
        sf.setLocation(node.get("lieu").asText());
        
		return sf;
	}
	
}
