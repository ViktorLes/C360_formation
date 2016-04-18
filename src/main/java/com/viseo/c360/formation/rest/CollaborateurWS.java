package com.viseo.c360.formation.rest;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;
import com.viseo.c360.formation.domain.collaborateur.Collaborateur;


@RestController
@RequestMapping(value = "${endpoint.collaborateurs}")
public class CollaborateurWS {

	@Inject
	CollaborateurDAO collaborateurDAO;
		
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public boolean addCollaborateur(@Valid @RequestBody Collaborateur myCollaborateur, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !collaborateurDAO.isMatriculeAlreadySaved(myCollaborateur.getMatricule())){
			collaborateurDAO.addCollaborateur(myCollaborateur);
			return true;
		}
		return false;
    }
    
}
