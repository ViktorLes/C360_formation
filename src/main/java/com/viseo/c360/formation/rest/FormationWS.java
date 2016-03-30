package com.viseo.c360.formation.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;
import com.viseo.c360.formation.rest.FormationWS.Test.TestDeserializer;

@RestController
public class FormationWS {

	@Inject
	FormationDAO formationDAO;
	
	@RequestMapping(value="${endpoint.formations}", method = RequestMethod.POST)
    @Transactional
    public void addFormation(@Valid @RequestBody Formation myFormation, BindingResult bindingResult){
		if(!(bindingResult.hasErrors())){
			formationDAO.addFormation(myFormation);
		}
    }
	
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.POST)
    @Transactional
    public boolean addSessionFormation(@Valid @RequestBody SessionFormation mySession, BindingResult bindingResult){
		
		if(!(bindingResult.hasErrors())){
			//formationDAO.addSessionFormation(mySession);
		}
		
		return true;
    }
	
	/*
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.GET)
    @Transactional
    public void addSession(){
		formationDAO.addSession();
    }
	
	@RequestMapping(value="/get", method = RequestMethod.GET)
    @Transactional
    @ResponseBody
    public SessionFormation getSession(){
		SessionFormation s = formationDAO.getSessionFormation(2);
			return s;
    }
    */
	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
    @Transactional
    public void testDeserialization(@RequestBody Test test){		
		System.out.println("message :"+test.getMessage());
    }
	
	@JsonDeserialize(using = TestDeserializer.class)
	public class Test{
		String message = "hello";
		
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			message = message;
		}
		
		public class TestDeserializer extends JsonDeserializer<Test> {
			
			public TestDeserializer() {
				super();
				// TODO Auto-generated constructor stub
			}

			@Override
		    public Test deserialize(JsonParser jsonParser,DeserializationContext deserializationContext) throws IOException {
		        ObjectCodec oc = jsonParser.getCodec();
		        JsonNode node = oc.readTree(jsonParser);
		        Test t = new Test();
		        t.setMessage("Coucou deserializer");
		        return t;
		    }
		}
	}
	
	
	
}
