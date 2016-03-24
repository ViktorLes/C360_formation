package com.viseo.c360.formation.rest;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void addSession(@Valid @RequestBody SessionFormation mySession, BindingResult bindingResult){
		if(!(bindingResult.hasErrors())){
			formationDAO.addSession();
		}
    }
	
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.GET)
    @Transactional
    public void addSession(){
			formationDAO.addSession();
    }
}
