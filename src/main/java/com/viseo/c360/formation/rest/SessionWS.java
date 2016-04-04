package com.viseo.c360.formation.rest;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.dao.SessionDAO;
import com.viseo.c360.formation.domain.formation.SessionFormation;

@RestController
@RequestMapping(value = "${endpoint.sessions}")
public class SessionWS {

//	@RequestMapping(method = RequestMethod.POST)
//  @Transactional
//
//  @ResponseBody
    /*public boolean addSession(@Valid @RequestBody SessionFormation mySession, BindingResult bindingResult){
	
		if(!(bindingResult.hasErrors())){
			
		SessionDAO.addSession(mySession);
		}
    }
*/
}