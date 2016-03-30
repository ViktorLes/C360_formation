package com.viseo.c360.formation.domain.formation;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.formation.SessionFormation.SessionFormationDeserializer;

@JsonDeserialize(using = SessionFormationDeserializer.class)
@Entity
public class SessionFormation {
	
	@Id
	@GeneratedValue
	long id;
	
	@Version
	long version;
	
	@ManyToOne
	Formation formation;
	
	@Temporal(TemporalType.DATE)
	Date firstDay;
	
	@Temporal(TemporalType.DATE)
	Date lastDay;
	
	@Temporal(TemporalType.TIME)
	Date firstTime;
	
	@Temporal(TemporalType.TIME)
	Date lastTime;
	
	String lieu;

	public SessionFormation() {
		super();
	}
	
	class SessionFormationDeserializer extends JsonDeserializer {

		@Inject
		FormationDAO formationDAO;
		
		@Override
		public SessionFormation deserialize(JsonParser parser, DeserializationContext context)
				throws IOException, JsonProcessingException {
			
			SessionFormation sf = new SessionFormation();
			ObjectCodec oc = parser.getCodec();
	        JsonNode node = oc.readTree(parser);
	        
	        sf.setFormation(formationDAO.getFormation(node.get("formation").asLong()));
//	        sf.setFirstDay(node.get("firstday").asText());
//	        node.get("lastday").asText();
//	        node.get("firsttime").asText();
//	        node.get("lasttime").asText();
	        sf.setLieu(node.get("lieu").asText());
	        
			return sf;
		}
		
	}

	public long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public Formation getFormation() {
		return formation;
	}

	public Date getFirstDay() {
		return firstDay;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public Date getFirstTime() {
		return firstTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public String getLieu() {
		return lieu;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public void setFirstDay(Date firstDay) {
		this.firstDay = firstDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	
	
	
}
