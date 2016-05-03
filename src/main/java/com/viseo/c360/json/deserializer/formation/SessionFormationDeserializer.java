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
import com.viseo.c360.formation.dao.TrainingDAO;
import com.viseo.c360.formation.domain.training.Training;
import com.viseo.c360.formation.domain.training.TrainingSession;

public class SessionFormationDeserializer extends JsonDeserializer<TrainingSession> {

	public class FormationDAOException extends JsonProcessingException{

		private static final long serialVersionUID = 1L;

		public FormationDAOException(String message) {
			super(message);
		}
	}
	
	@Inject
	TrainingDAO trainingDAO;
	
	@Override
	public TrainingSession deserialize(JsonParser parser, DeserializationContext context) throws JsonProcessingException, IOException {

		SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
		Date beginning = null;
		Date ending = null;
	
		TrainingSession sf = new TrainingSession();
		ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        
        //Training
        Long trainingId = node.get("training").asLong();
        Training training = trainingDAO.getTraining(trainingId);
        if(training == null) {
        	System.err.println("Impossible d'obtenir l'objet 'Training' d'Id : "+Long.toString(trainingId)+".");
        	throw new FormationDAOException("Impossible d'obtenir l'objet 'Training' d'Id : "+Long.toString(trainingId)+".");
        }
		sf.setTraining(training);
		
		//dates et heures
        try {
        	beginning = formatterDate.parse(node.get("beginning").asText());
        	ending = formatterDate.parse(node.get("ending").asText());
        	sf.setBeginning(beginning);
        	sf.setEnding(ending);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new FormationDAOException("Problème de format de date/heure.");
		}
        
        //lieu
        sf.setLocation(node.get("location").asText());
        
		return sf;
	}
	
}
