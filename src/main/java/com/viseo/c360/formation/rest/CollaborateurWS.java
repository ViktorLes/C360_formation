package com.viseo.c360.formation.rest;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;

@RestController
@RequestMapping(value = "${endpoint.collaborateurs}")
public class CollaborateurWS {
	
	@Inject
	CollaborateurDAO collaborateurDAO;
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String addCollaborateur(@RequestBody String content){
		
		//check var sent !
		if(true){
			//persist the data
			collaborateurDAO.addCollaborateur("...", "k", "jj");
		}
		
        return "collaborateur ajouté !";
    }
}
