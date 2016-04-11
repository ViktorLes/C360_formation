package com.viseo.c360.json.deserializer.formation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.formation.SessionFormation;

public class SessionFormationDeserializer extends JsonDeserializer<SessionFormation> {

	@Inject
	FormationDAO formationDAO;
	
	@Override
	public SessionFormation deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {

		SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat formatterHeure = new SimpleDateFormat("HH:mm");
	
		SessionFormation sf = new SessionFormation();
		ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        
        sf.setFormation(formationDAO.getFormation(node.get("formation").asLong()));
        try {
        	sf.setDateDebut(formatterDate.parse(node.get("dateDebut").asText()));
        	sf.setDateFin(formatterDate.parse(node.get("dateFin").asText()));
        	sf.setHeureDebut(formatterHeure.parse(node.get("heureDebut").asText()));
        	sf.setHeureFin(formatterHeure.parse(node.get("heureFin").asText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        sf.setLieu(node.get("lieu").asText());
        
		return sf;
	}
	
}
