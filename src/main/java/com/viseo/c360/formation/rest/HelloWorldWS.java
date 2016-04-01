package com.viseo.c360.formation.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;


@RestController
public class HelloWorldWS {
	
	
	
	public HelloWorldWS() {
		super();
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule testModule = new SimpleModule("MyModule")
			.addSerializer(Essai.class, new MonSerializer());
		mapper.registerModule(testModule);
	}

	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    @ResponseBody
    //@Transactional
    public Essai sayHelloWorld(){
		
		//messageDAO.addCoucou();
        return new Essai("coucou");
    }
	
	@JsonSerialize(using = MonSerializer.class)
	public final class Essai{
		
		private String Message;
		
		public Essai(String m){
			Message = m;
		}
		
		public String getMessage() {
			return Message;
		}

		public void setMessage(String message) {
			Message = message;
		}
		
	}

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

	
}

