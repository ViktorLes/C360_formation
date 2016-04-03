package com.viseo.c360.formation.rest;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.viseo.c360.formation.rest.HelloWorldWS.Essai;

public class MonSerializer extends JsonSerializer<Essai> {
	
	public MonSerializer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(Essai value, JsonGenerator gson, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		
		gson.writeStartObject();
		
		gson.writeStringField("craspec", value.getMessage());
		
		gson.writeEndObject();
	}
	
}

