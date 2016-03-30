package com.viseo.c360.formation.rest;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;
import com.viseo.c360.formation.dao.FormationDAO;
import com.viseo.c360.formation.domain.collaborateur.Collaborateur;
import com.viseo.c360.formation.domain.formation.Formation;

@RestController
@RequestMapping(value="${endpoint.formations}")
public class FormationWS {

	@Inject
	FormationDAO formationDAO;
	
	@RequestMapping(method = RequestMethod.POST)
    @Transactional
    public void addFormation(@Valid @RequestBody Formation myFormation, BindingResult bindingResult){
		
		//check data sent !
		if(!(bindingResult.hasErrors())){
			int a = myFormation.getNombredemijournee();
			String b = Integer.toString(a);
			System.out.println(b);
			
//		Integer.toString(myFormation.getNombredemijournee());
//		System.out.println(getNombredemijournee());
			
			//if valid : persist the data
			formationDAO.addFormation(myFormation);
		}
    }
}
