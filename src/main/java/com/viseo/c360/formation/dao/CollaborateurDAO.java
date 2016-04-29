package com.viseo.c360.formation.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.viseo.c360.formation.domain.collaborateur.DemandeFormation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.collaborateur.Collaborateur;

@Repository
public class CollaborateurDAO {
	
	@PersistenceContext
	EntityManager em;

	/***Collaborateur***/
	public Collaborateur getCollaborateur(long id) {return em.find(Collaborateur.class, id);}

	@Transactional
	public void addCollaborateur(Collaborateur c){
		em.persist(c);
	}
	
	@Transactional
	public boolean isMatriculeAlreadySaved(String matricule){

		Collection<Collaborateur> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<Collaborateur> q = cb.createQuery(Collaborateur.class);
		  Root<Collaborateur> c = q.from(Collaborateur.class);
		//  ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("matricule"), matricule));
		  
		  list = (Collection<Collaborateur>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty();
	}

	/***DemandeFormation***/
	@Transactional
	public void addDemandeFormation(DemandeFormation dmf){
		em.persist(dmf);
	}


}
