package com.viseo.c360.formation.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.viseo.c360.formation.domain.formation.SessionFormation;

@Repository
public class SessionDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	private void addSession (String titreFormation, String date, String heureDeb, String heureFin, String lieu) {
		
		SessionFormation S = new SessionFormation();
		S.setTitreformation(titreFormation);
		S.setDate(date);
		S.setHeureDeb(heureDeb);
		S.setHeureFin(heureFin);
		S.setLieu(lieu);
		
		em.persist(S);
	}
	
	@Transactional
	public void addSession(SessionFormation S){
		em.persist(S);		
	}
}