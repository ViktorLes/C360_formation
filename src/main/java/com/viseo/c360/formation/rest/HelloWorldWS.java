package com.viseo.c360.formation.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



@RestController
public class HelloWorldWS {

	@RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    @ResponseBody
    //@Transactional
    public Essai sayHelloWorld(){
		
		//messageDAO.addCoucou();
        return new Essai("coucou");
    }
	
	//@JsonSerialize(using = MonSerializer.class)
	public class Essai{
		
		
		
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
	
	
	
}

