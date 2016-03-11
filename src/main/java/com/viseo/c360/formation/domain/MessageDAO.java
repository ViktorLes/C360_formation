package com.viseo.c360.formation.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MessageDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addCoucou(){
		em.persist(new Message("Coucou"));
	}
}
