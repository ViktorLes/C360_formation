package com.viseo.c360.formation.rest;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.viseo.c360.formation.domain.Message;
import com.viseo.c360.formation.domain.MessageDAO;

@RestController
public class HelloWorldWS {
	
	@Inject
	MessageDAO messageDAO;
	
	@RequestMapping(value = "${endpoint.helloworld}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public Essai sayHelloWorld(){
		
		messageDAO.addCoucou();
		
        return new Essai();
    }
	
	//@JsonSerialize(using = MonSerializer.class)
	public final class Essai{
		String Message = "hello";
		
		public String getMessage() {
			return Message;
		}

		public void setMessage(String message) {
			Message = message;
		}
		
	}
/*
	class MonSerializer extends JsonSerializer<Essai> {

		@Override
		public void serialize(Essai value, JsonGenerator gson, SerializerProvider arg2)
				throws IOException, JsonProcessingException {
			// TODO Auto-generated method stub
			
			gson.writeStartObject();
			
			gson.writeStringField("msg", value.getMessage());
			
			gson.writeEndObject();
		}
		
	}
*/
	
}

