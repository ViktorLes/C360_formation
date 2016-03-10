package com.viseo.c360.formation.rest;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viseo.c360.formation.domain.Message;

@RestController
public class HelloWorldWS {
	
	@PersistenceContext
	EntityManager em;
	
	@RequestMapping(value = "${endpoint.helloworld}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String sayHelloWorld(){
		
		em.persist(new Message("Coucou"));
		
        return "Hello world";
    }

	
}

