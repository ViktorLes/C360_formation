package com.viseo.c360.formation.rest;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String addCollaborateur(@Validated @RequestBody Collaborateur myCollaborateur, BindingResult bindingResult){

		//check var sent !
		if(!(bindingResult.hasErrors())){
			//persist the data
			collaborateurDAO.addCollaborateur(myCollaborateur);
		}
        return "collaborateur ajouté !";
    }
//	
//	@RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    @Transactional
//    public String getCollaborateurs(){		
//        return "collaborateurs lecture !";
//    }
}
