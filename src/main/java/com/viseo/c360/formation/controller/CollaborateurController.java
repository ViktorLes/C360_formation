package com.viseo.c360.formation.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;
import com.viseo.c360.formation.domain.collaborateur.Collaborateur;


@Controller
@RequestMapping(value = "${endpoint.collaborateurs}")
public class CollaborateurController {
	@Inject
	CollaborateurDAO collaborateurDAO;
	
	@Inject
    private Validator validator;
	
	@RequestMapping(method = RequestMethod.POST)
    @Transactional
    public String addCollaborateur(@Valid @RequestBody Collaborateur myCollaborateur,BindingResult bindingResult){

		//Set<ConstraintViolation<Collaborateur>> a = validator.validate(myCollaborateur, Collaborateur.class);
		
		//check var sent !
		if(!(bindingResult.hasErrors())){
			System.out.println("COUCOU" + myCollaborateur.getMatricule());
			System.out.println("COUCOU" + myCollaborateur.getNom());
			//persist the data
			collaborateurDAO.addCollaborateur(myCollaborateur);
		}
		
        return "collaborateur ajouté !";
    }
}
