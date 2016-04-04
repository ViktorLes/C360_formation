package com.viseo.c360.formation.dao;

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
	public SessionFormation getSessionFormation(long id){
		return em.find(SessionFormation.class, id);
	}
	
	@Transactional
	public Formation getFormation(long id){
		return em.find(Formation.class, id);
	}
	
	@Transactional
	public void addFormation(Formation F){
		em.persist(F);
	}
	
	@Transactional
	public void addSessionFormation(SessionFormation sf){
		em.persist(sf);
	}
	
//	@Transactional
//	public void addSession(){
//		
////		long idFormation = 1;
////		em.find(Formation.class, idFormation);
//
//		Formation myFormation = new Formation();
//		myFormation.setTitreformation("formationA");
//		myFormation.setNombredemijournee(7);
//		
//		Formation myFormation2 = new Formation();
//		myFormation2.setTitreformation("formationB");
//		myFormation2.setNombredemijournee(10);
//		
//		Date firstDay = new Date();
//		firstDay.setYear(1993);
//		firstDay.setMonth(4);
//		firstDay.setDate(30);
//			
//		Date lastDay = new Date();
//		lastDay.setYear(1993);
//		lastDay.setMonth(4);
//		lastDay.setDate(30);
//		
//		Date firstTime = new Date();
//			firstTime.setHours(10);
//			firstTime.setMinutes(10);
//
//		Date lastTime = new Date();
//			lastTime.setHours(10);
//			lastTime.setMinutes(10);
//
//		
//		this.addFormation(myFormation);
//		
//		SessionFormation sessionFormation = new SessionFormation();
//		sessionFormation.setFormation(myFormation);
//		sessionFormation.setFirstDay(firstDay);
//		sessionFormation.setLastDay(lastDay);
//		sessionFormation.setFirstTime(firstTime);
//		sessionFormation.setLastTime(lastTime);
//		em.persist(sessionFormation);
//		em.flush();
//	}

	
	
}
