package com.viseo.c360.json.deserializer.formation;

import java.io.IOException;

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
		
		System.out.println("deserializer");
		
		SessionFormation sf = new SessionFormation();
		ObjectCodec oc = parser.getCodec();
        JsonNode node = oc.readTree(parser);
        
        sf.setFormation(formationDAO.getFormation(node.get("formation").asLong()));
//        sf.setFirstDay(node.get("firstday").asText());
//        node.get("lastday").asText();
//        node.get("firsttime").asText();
//        node.get("lasttime").asText();
        sf.setLieu(node.get("lieu").asText());
        
		return sf;
	}
	
}
