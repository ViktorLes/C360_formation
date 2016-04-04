package com.viseo.c360.formation.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.viseo.c360.formation.domain.formation.Formation;

@Repository
public class FormationDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void addFormation(String titreformation,int nombredemijournee){
		
		Formation F = new Formation();
		F.setTitreformation(titreformation);
		F.setNombredemijournee(nombredemijournee);
		
		em.persist(F);
	}
	
	@Transactional
	public void addFormation(Formation F){
		em.persist(F);
	}
	
	@Transactional
	public boolean isFormationAlreadySaved(String titreFormation){

		Collection<Formation> list = null;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		 
		  CriteriaQuery<Formation> q = cb.createQuery(Formation.class);
		  Root<Formation> c = q.from(Formation.class);
		  ParameterExpression<String> p = cb.parameter(String.class);
		  q.select(c).where(cb.equal(c.get("titreformation"), titreFormation));
		  
		  list = (Collection<Formation>) em.createQuery(q).getResultList();
		  
		return !list.isEmpty(); //return true if the list is not avoid
	}
}
