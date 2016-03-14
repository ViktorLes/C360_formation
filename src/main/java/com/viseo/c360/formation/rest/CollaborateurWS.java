package com.viseo.c360.formation.rest;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;

@RestController
public class CollaborateurWS {
	@Inject
	CollaborateurDAO collaborateurDAO;
	
	@RequestMapping(value = "${endpoint.postCollaborateurs}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String addCollaborateur(
    		/*
    		@PathVariable(value="Matricule") String matriculePost, 
    		@PathVariable(value="Nom") String nomPost,
    		@PathVariable(value="Prenom") String prenomPost*/){
		//check var sent !
		//...
		if(true){
			//persist the data
			collaborateurDAO.addCollaborateur("sjfozij098", "KALMOUNI", "Nada");
		}
		
        return "collaborateur ajouté !";
    }
}
