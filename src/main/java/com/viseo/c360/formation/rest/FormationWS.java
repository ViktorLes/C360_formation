package com.viseo.c360.formation.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;

@RestController
public class FormationWS {

	@Inject
	FormationDAO formationDAO;
	
	//Formation
	@RequestMapping(value="${endpoint.formations}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addFormation(@Valid @RequestBody Formation myFormation, BindingResult bindingResult){
		
		if(!(bindingResult.hasErrors()) && !formationDAO.isFormationAlreadySaved(myFormation.getTitreformation())){
			formationDAO.addFormation(myFormation);
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "${endpoint.formations}", method = RequestMethod.GET)
	@ResponseBody
    public List<Formation> ReadFormation(){	
		return formationDAO.GetAllFormation();
	}
	
	//SessionFormation
	@RequestMapping(value="${endpoint.sessions}", method = RequestMethod.POST)
    @ResponseBody
    public boolean addSessionFormation(@Valid @RequestBody SessionFormation mySessionFormation, BindingResult bindingResult){
		
		if(!(bindingResult.hasErrors()) /*&& !formationDAO.isSessionFormationAlreadySaved(mySessionFormation.getTitreformation()) **/){
			formationDAO.addSessionFormation(mySessionFormation);
			return true;
		}
		return false;
    }
}	