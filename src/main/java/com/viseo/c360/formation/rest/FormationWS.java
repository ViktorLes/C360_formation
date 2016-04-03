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
    @ResponseBody
    public boolean addSessionFormation(@Valid @RequestBody SessionFormation mySessionFormation, BindingResult bindingResult){     

		System.out.println("deserialisation effective");
		if(!(bindingResult.hasErrors())){
			formationDAO.addSessionFormation(mySessionFormation);
			System.out.println("sessionFormation valid");
		}
		return true;
    }
	
	
}
