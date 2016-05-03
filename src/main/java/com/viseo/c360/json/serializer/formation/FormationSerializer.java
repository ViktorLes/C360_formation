package com.viseo.c360.json.serializer.formation;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.viseo.c360.formation.domain.training.Training;

public class FormationSerializer  extends JsonSerializer<Training> {
		
		public FormationSerializer() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void serialize(Training value, JsonGenerator gson, SerializerProvider arg2)
				throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub
			
			gson.writeStartObject();
			
			gson.writeStringField("label", value.getTrainingTitle());
			gson.writeStringField("Id",Long.toString(value.getId()));

			gson.writeEndObject();
		}
		
	}