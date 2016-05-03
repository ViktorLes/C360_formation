package com.viseo.c360.formation.services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.viseo.c360.formation.domain.collaborateur.Collaborator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.CollaborateurDAO;


@RestController
public class CollaborateurWS {

	@Inject
	CollaborateurDAO collaborateurDAO;
		
	@RequestMapping(value = "${endpoint.collaborateurs}",method = RequestMethod.POST)
    @ResponseBody
    public boolean addCollaborateur(@Valid @RequestBody Collaborator myCollaborator, BindingResult bindingResult){
		if(!(bindingResult.hasErrors()) && !collaborateurDAO.isMatriculePersistent(myCollaborator.getPersonnalIdNumber())){
			collaborateurDAO.addCollaborateur(myCollaborator);
			return true;
		}
		return false;
    }
	
	@RequestMapping(value = "${endpoint.collaborateurs}", method = RequestMethod.GET)
	@ResponseBody
    public List<Collaborator> readAllCollaborateur(){
		return collaborateurDAO.getAllCollaborateur();
	}
    
}
