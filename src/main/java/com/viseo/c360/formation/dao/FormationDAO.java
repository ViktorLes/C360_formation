package com.viseo.c360.formation.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.formation.Formation;
import com.viseo.c360.formation.domain.formation.SessionFormation;

@Repository
public class FormationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addFormation(Formation F){
		em.persist(F);
	}
	
	@Transactional
	public void addSession(){
		
//		long idFormation = 1;
//		em.find(Formation.class, idFormation);

		Formation myFormation = new Formation();
		myFormation.setTitreformation("formationA");
		myFormation.setNombredemijournee(7);
		
		Formation myFormation2 = new Formation();
		myFormation2.setTitreformation("formationB");
		myFormation2.setNombredemijournee(10);

		SessionFormation sessionFormation = new SessionFormation();
		sessionFormation.setFormation(myFormation);
		this.addFormation(myFormation);
		em.persist(sessionFormation);
	}
}
