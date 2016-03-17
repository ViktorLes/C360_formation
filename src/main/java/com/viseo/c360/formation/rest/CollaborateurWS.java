package com.viseo.c360.formation.rest;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;
import com.viseo.c360.formation.domain.collaborateur.Collaborateur;


@RestController
@RequestMapping(value = "${endpoint.collaborateurs}")
public class CollaborateurWS {

	@Inject
	CollaborateurDAO collaborateurDAO;
	
	@RequestMapping(method = RequestMethod.POST)
    @Transactional
    public void addCollaborateur(@Valid @RequestBody Collaborateur myCollaborateur, BindingResult bindingResult){
		
		//check data sent !
		if(!(bindingResult.hasErrors())){
			//if valid : persist the data
			collaborateurDAO.addCollaborateur(myCollaborateur);
		}
    }
}
